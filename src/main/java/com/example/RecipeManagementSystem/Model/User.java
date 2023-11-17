package com.example.RecipeManagementSystem.Model;

import com.example.RecipeManagementSystem.Model.Enum.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank
    private String userName;

    @Pattern(regexp = "^\\w+@gmail\\.com$")
    private String userEmail;

    @NotBlank
    private String userPassword;

    @Enumerated(EnumType.STRING)
    private Gender userType;

    @Pattern(regexp = "^\\d{10}$")
    private String userContact;
}
