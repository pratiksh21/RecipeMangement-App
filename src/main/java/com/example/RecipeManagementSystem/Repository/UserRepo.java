package com.example.RecipeManagementSystem.Repository;

import com.example.RecipeManagementSystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    User findFirstByUserEmail(String newEmail);


}
