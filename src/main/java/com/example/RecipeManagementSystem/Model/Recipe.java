package com.example.RecipeManagementSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;

    @NotBlank
    private String recipeName;

    @NotBlank
    private String recipeIngredients;

    @NotBlank
    private String recipeInstruction;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;  //many recipe link to one user
}
