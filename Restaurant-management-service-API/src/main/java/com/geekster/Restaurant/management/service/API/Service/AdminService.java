package com.geekster.Restaurant.management.service.API.Service;

import com.geekster.Restaurant.management.service.API.Model.Admin;
import com.geekster.Restaurant.management.service.API.Model.dto.SignUpOutput;
import com.geekster.Restaurant.management.service.API.Repository.IAdminRepo;
import com.geekster.Restaurant.management.service.API.Service.HashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    IAdminRepo iAdminRepo;
    public SignUpOutput addAdmin(Admin admin) {
        String email = admin.getAdminEmail();

        String signUpStatusMessage = "";
        if(email==null)
        {
            signUpStatusMessage = "Enter a Valid Email";
            return new SignUpOutput(false,signUpStatusMessage);
        }

        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(email);

        if(existingAdmin!=null)
        {
            signUpStatusMessage = "Email Already Registered";
            return  new SignUpOutput(false ,signUpStatusMessage );
        }

        try {
            String encryptPass = PasswordEncrypter.encryptPassword(admin.getAdminPassword());
            admin.setAdminPassword(encryptPass);
            iAdminRepo.save(admin);
            
            signUpStatusMessage = "New Admin Registered";
            
            return new SignUpOutput(true,signUpStatusMessage);
        }
        catch (Exception e)
        {
            signUpStatusMessage = "Internal error occurred ";
            
            return new SignUpOutput(false,signUpStatusMessage);
        }

    }


    public boolean ifAdminExistOrNot(String adminEMail) {
        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(adminEMail);

       return existingAdmin!=null;
    }
}
