package com.example.RecipeManagementSystem.Repository;

import com.example.RecipeManagementSystem.Model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe,Integer> {
    Recipe findByRecipeName(String name);



}
