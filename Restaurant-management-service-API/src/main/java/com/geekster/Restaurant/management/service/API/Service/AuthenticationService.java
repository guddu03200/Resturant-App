package com.geekster.Restaurant.management.service.API.Service;

import com.geekster.Restaurant.management.service.API.Model.AuthenticationToken;
import com.geekster.Restaurant.management.service.API.Repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationRepo iAuthenticationRepo;

    public void saveAuthToken(AuthenticationToken auth) {
        iAuthenticationRepo.save(auth);
    }

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = iAuthenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }
}
