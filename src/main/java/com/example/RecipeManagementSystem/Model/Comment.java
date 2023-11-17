package com.example.RecipeManagementSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotBlank
    private String commentDesc;

    private LocalDateTime commentCreationTimestamp;

    @ManyToOne
    @JoinColumn(name = "fk_recipe_id")
    private Recipe recipe;  //many comments link to the one recipe

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;  //many comments link to the one user


}
