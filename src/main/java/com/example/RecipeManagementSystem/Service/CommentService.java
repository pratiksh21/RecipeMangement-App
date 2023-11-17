package com.example.RecipeManagementSystem.Service;

import com.example.RecipeManagementSystem.Model.Comment;
import com.example.RecipeManagementSystem.Repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
   @Autowired
   CommentRepo commentRepo;


   public String addComment(Comment comment) {
       commentRepo.save(comment);
      return "Commented successfully on recipe ";
   }
}
