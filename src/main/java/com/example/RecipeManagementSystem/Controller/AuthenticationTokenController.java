package com.example.RecipeManagementSystem.Controller;

import com.example.RecipeManagementSystem.Service.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationTokenController {
    @Autowired
    AuthenticationTokenService authenticationTokenService;
}
