package com.reply.springdemo.controller;

import com.reply.springdemo.dto.PostCommentDto;
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
        return postCommentService.getAllPostComment().getBody();
    }

    @GetMapping({"/postCommentsById/{id}" , "/postCommentsById/"})
    public ResponseEntity<List<PostComment>> getAllPostCommentsById_(@PathVariable Optional<String> id){
        System.out.println("ID : " + id.orElse("no ID"));
        return postCommentService.getAllPostComment();
    }

    @GetMapping("/postCommentsByReview")
    public ResponseEntity<List<PostCommentDto>> findAllByReview(@RequestParam String review) {
        System.out.println("ID:" + review);
        return ResponseEntity.ok(postCommentService.findAllByReview(review));
    }

    @PostMapping("/insertComment")
    public ResponseEntity<String> insert(@RequestParam(value = "review") String review){
        postCommentService.insertCommentWithJdbcTemplate(review);
       return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
    }

    @GetMapping("/postCommentsByIdJdbc")
    public PostComment getAllPostCommentsByIdJdbc(@RequestParam(value = "id", required = false) String id){
        return postCommentService.selectById(id);
    }
}
