package com.reply.springdemo.controller;

import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/postComments")
    public ResponseEntity<List<PostComment>> getAllPostComment(@RequestParam(value = "page") Integer page, @RequestParam(value = "quantity") Integer quantity){
      return postCommentService.getAllPostCommentPaginated(page, quantity);
    }

    @GetMapping("/postCommentsById")
    public List<PostComment> getAllPostCommentsById(@RequestParam(value = "id", required = false) String id){
        System.out.println("ID : " + id);
        return null;
        //return postCommentService.getAllPostComment();
    }

   // @GetMapping({"/postCommentsById/{id}" , "/postCommentsById/"})
    public ResponseEntity<List<PostComment>> getAllPostCommentsById_(@PathVariable Optional<String> id){
        System.out.println("ID : " + id.orElse("no ID"));
        return postCommentService.getAllPostComment();
       // return postCommentService.getAllPostComment();
    }

}
