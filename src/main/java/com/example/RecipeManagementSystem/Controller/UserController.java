package com.example.RecipeManagementSystem.Controller;

import com.example.RecipeManagementSystem.Model.User;
import com.example.RecipeManagementSystem.Service.AuthenticationTokenService;
import com.example.RecipeManagementSystem.Service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;

    @PostMapping("user/signUp")
    public String signUpUser(@RequestBody @Valid User user){
        return userService.signUpUser(user);
    }

    @PostMapping("user/signIn")
    public String signInUser(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String userEmail , @RequestParam @NotBlank String password ){
        return userService.signInUser(userEmail , password);
    }

    @DeleteMapping("user/signOut")
    public String signOut(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam String tokenValue){
        if(authenticationTokenService.authenticate(email , tokenValue)){
            return userService.signOut(email);
        }else{
            return "Invalid credentials or you are already sign out";
        }
    }

    @GetMapping("users")
    public List<User> getAllUsers(@RequestParam @Pattern(regexp = "^\\w+@gmail\\.com$") String email , @RequestParam String tokenValue){
        if(authenticationTokenService.authenticate(email , tokenValue)){
            return userService.getAllUsers();
        }
        return null; // We can also throw exception here
    }
}
