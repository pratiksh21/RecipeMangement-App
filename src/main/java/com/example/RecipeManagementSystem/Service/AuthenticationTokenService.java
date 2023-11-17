package com.example.RecipeManagementSystem.Service;

import com.example.RecipeManagementSystem.Model.AuthenticationToken;
import com.example.RecipeManagementSystem.Model.User;
import com.example.RecipeManagementSystem.Repository.AuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenService {
    @Autowired
    AuthenticationTokenRepo authenticationTokenRepo;

    public void save(AuthenticationToken token) {
        authenticationTokenRepo.save(token);
    }

    public boolean authenticate(String email, String tokenValue)
    {
        AuthenticationToken token = authenticationTokenRepo.findFirstByTokenValue(tokenValue);

        if(token == null)
        {
            return false;
        }

        String tokenConnectedEmail = token.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }

    public void delete(AuthenticationToken token) {
        authenticationTokenRepo.delete(token);
    }


    public AuthenticationToken findFirstByUser(User user) {
         return authenticationTokenRepo.findFirstByUser(user);
    }
}
