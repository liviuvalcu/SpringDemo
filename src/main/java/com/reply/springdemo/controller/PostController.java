package com.reply.springdemo.controller;

import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<List<PostComment>> getAllPostComment(@RequestParam(value = "page") Integer page, @RequestParam(value = "quantity") Integer quantity) {
        return postCommentService.getAllPostCommentPaginated(page, quantity);
    }

    @GetMapping("/postCommentsById")
    public List<PostComment> getAllPostCommentsById(@RequestParam(value = "id", required = false) String id) {
        System.out.println("ID : " + id);
        return postCommentService.getAllPostComment().getBody();
    }

    @GetMapping({"/postCommentsById/{id}", "/postCommentsById/"})
    public ResponseEntity<List<PostComment>> getAllPostCommentsById_(@PathVariable Optional<String> id) {
        System.out.println("ID : " + id.orElse("no ID"));
        return postCommentService.getAllPostComment();
    }

    @GetMapping({ "/postCommentsByPagination/{id}"})
    public ResponseEntity<PostComment> selectByIdPaginated(@PathVariable Long id, @PathVariable Integer integer, @PathVariable Integer quantity) {

        return postCommentService.selectByIdPaginated(id, integer,quantity);
    }

    @GetMapping({ "/postCommentsSelectById/{id}"})
    public ResponseEntity<PostComment> selectById(@PathVariable Long id) {

        return postCommentService.selectById(id);
    }

    @GetMapping({"/postCommentsFindByStatus/{status}"})
    public ResponseEntity<List<PostComment>> findByStatus(@PathVariable String status) {

        return postCommentService.findByStatus(status);
    }

    @GetMapping({"/postCommentsFindDistinctByVotes/{votes}"})
    public ResponseEntity<List<PostComment>> findDistinctByVotes(@PathVariable Integer votes) {

        return postCommentService.findDistinctByVotes(votes);
    }


}
