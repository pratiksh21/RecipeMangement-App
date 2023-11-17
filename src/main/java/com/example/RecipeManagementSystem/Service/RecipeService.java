package com.example.RecipeManagementSystem.Service;

import com.example.RecipeManagementSystem.Model.Recipe;
import com.example.RecipeManagementSystem.Model.User;
import com.example.RecipeManagementSystem.Repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service public class RecipeService {
    @Autowired RecipeRepo recipeRepo;

    @Autowired UserService userService;

    public String addRecipe(Recipe recipe) {

        recipeRepo.save(recipe);
        return "Recipe added successfully..";
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    public Recipe getRecipeByName(String name) {
        return recipeRepo.findByRecipeName(name);
    }

    public Recipe getRecipeById(Integer id) {
        return recipeRepo.findById(id).orElse(null);
    }

    public String updateIngredients( String email , Integer id, String ingredients) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if(recipe != null){
            if(recipe.getUser().equals(user)){ // means if they are equal then that user is owner of that post or recipe.
                recipe.setRecipeIngredients(ingredients);
                recipeRepo.save(recipe);
                return "Ingredients updated successfully for the recipe "+recipe.getRecipeName();
            }else{
                return "You are not authorized user to update recipe only owner can update it.";
            }
        }
        return "Recipe not found with id "+ id;
    }

    public String updateInstructions( String email ,Integer id, String instructions) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if (recipe != null) {
            if(recipe.getUser().equals(user)) {
                recipe.setRecipeInstruction(instructions);
                recipeRepo.save(recipe);
                return "Instructions updated successfully for the recipe " + recipe.getRecipeName();
            } else {
                return "You are not authorized user to update recipe only owner can update it.";
            }
        }
        return "Recipe not found with id " + id;
    }

    public String deleteRecipe( String email ,Integer id) {
        User user = userService.findFirstEmail(email);
        Recipe recipe = recipeRepo.findById(id).orElse(null);

        if (recipe != null) {
            if (recipe.getUser().equals(user)) {
                recipeRepo.delete(recipe);
                return "Recipe for id " + id + " deleted successfully..";
            } else {
                return "You are not authorized user to delete recipe only owner can delete it.";
            }
        }
        return "Recipe not found with id " + id;
    }

}
