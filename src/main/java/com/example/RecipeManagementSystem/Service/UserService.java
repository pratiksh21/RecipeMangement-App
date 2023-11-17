package com.example.RecipeManagementSystem.Service;

import com.example.RecipeManagementSystem.Model.AuthenticationToken;
import com.example.RecipeManagementSystem.Model.User;
import com.example.RecipeManagementSystem.Repository.UserRepo;
import com.example.RecipeManagementSystem.Service.Utility.EmailHandler;
import com.example.RecipeManagementSystem.Service.Utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class UserService {
    @Autowired UserRepo userRepo;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    public String signUpUser(User user) {

        String newEmail = user.getUserEmail();



        // check if user already exists means already signUp

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            return  "User already registered..Please signIn to use our service..";

        }

        // If it cant able to return anything means the user is new user, so we need save the user
        // By hashing the password: encrypt the password to security purpose
        try {
            String encryptedPassword = PasswordEncrypter.encrypt(user.getUserPassword());


            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return "User registered successfully!!!";
        }
        catch(Exception e)
        {
            return "Internal error occurred during sign up or invalid credentials.Please try again ..";

        }
    }

    public String signInUser(String userEmail, String password) {


        //check if user is exists or not
        User existingUser = userRepo.findFirstByUserEmail(userEmail);

        if(existingUser == null)
        {
            return  "User not registered please signUp!!!";
        }

        // if we cant able to return then it means user exist so need to verify and create session for user

        try {
            String encryptedPassword = PasswordEncrypter.encrypt(password);

            if(existingUser.getUserPassword().equals(encryptedPassword))
            {

                AuthenticationToken token  = new AuthenticationToken(existingUser);
                authenticationTokenService.save(token);

                EmailHandler.sendEmail(userEmail,"Authentication token received via signing in",token.getTokenValue());
                return "Token sent to your registered email";
            }
            else {
                return "Invalid credentials!!!";
            }
        }
        catch(Exception e)
        {
            return  "Internal error occurred during sign in";
        }
    }

    public String signOut(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken token = authenticationTokenService.findFirstByUser(user);
        authenticationTokenService.delete(token);
        return "Sign out successfully..";
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findFirstEmail(String email) {
        return userRepo.findFirstByUserEmail(email);
    }
}
