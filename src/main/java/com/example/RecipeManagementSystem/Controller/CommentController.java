package com.example.RecipeManagementSystem.Controller;

import com.example.RecipeManagementSystem.Model.Comment;
import com.example.RecipeManagementSystem.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("comment")
    public String addComment(@RequestBody @Valid Comment comment){
        return commentService.addComment(comment);
    }
}
