package com.example.RecipeManagementSystem.Repository;

import com.example.RecipeManagementSystem.Model.AuthenticationToken;
import com.example.RecipeManagementSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByUser(User user);

    AuthenticationToken findFirstByTokenValue(String tokenValue);
}
