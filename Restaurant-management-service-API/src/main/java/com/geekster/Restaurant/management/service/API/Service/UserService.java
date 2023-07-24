package com.geekster.Restaurant.management.service.API.Service;

import com.geekster.Restaurant.management.service.API.Model.AuthenticationToken;
import com.geekster.Restaurant.management.service.API.Model.User;
import com.geekster.Restaurant.management.service.API.Model.dto.SignInInput;
import com.geekster.Restaurant.management.service.API.Model.dto.SignUpOutput;
import com.geekster.Restaurant.management.service.API.Repository.IUserRepo;
import com.geekster.Restaurant.management.service.API.Service.HashingUtility.PasswordEncrypter;
import com.geekster.Restaurant.management.service.API.Service.emailUtility.EmailHandler;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;
    public SignUpOutput signUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = "";

        String newEmail = user.getUserEmail();

        if(newEmail==null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Please enter A valid Email";

            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser!=null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Email already Registered";

            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        try
        {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

             signUpStatusMessage = "New User Registered";
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        catch (Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);

        }

    }

    public String signInUser(SignInInput signInInput) {

        String userEmail = signInInput.getEmail();
        String signInStatusMessage = "";
        if(userEmail ==null)
        {
            signInStatusMessage = "Please Enter a Valid Email";
            return signInStatusMessage;
        }


        User existingUser = userRepo.findFirstByUserEmail(signInInput.getEmail());

        if(existingUser==null)
        {
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }
      //Matching Credentials
        try{
            String encryptedPass = PasswordEncrypter.encryptPassword(signInInput.getPassword());

            if(existingUser.getUserPassword().equals(encryptedPass))
            {
                AuthenticationToken auth = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(auth);

               // EmailHandler.sendEmail("tanishtgupta42@gmail.com","Authentication",auth.getTokenValue());
                signInStatusMessage = "Signed In Successfully";
                return signInStatusMessage;
            }
            else
            {
                signInStatusMessage = "Invalid Credentials";
                return  signInStatusMessage;
            }
        }
        catch (Exception e)
        {
            signInStatusMessage = "Internal error occurred during Sign in";
            return  signInStatusMessage;
        }
    }

    public User findFirstByUserEmail(String email) {
            return userRepo.findFirstByUserEmail(email);
    }
}


















