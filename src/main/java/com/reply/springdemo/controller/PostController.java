package com.reply.springdemo.controller;

import com.reply.springdemo.dto.PostCommentDto;
import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.service.PostCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "basic")
public class PostController {

    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("/postComments")
    @Operation(summary = "getAllPostComment")
    //@Secured(value = "ROLE_ADMIN")
    @PostAuthorize(value = "returnObject.body.size() == 2")
    public ResponseEntity<List<PostComment>> getAllPostComment(@RequestParam(value = "page") Integer page, @RequestParam(value = "quantity") Integer quantity){
      return postCommentService.getAllPostCommentPaginated(page, quantity);
    }

    @GetMapping("/postCommentsById")
    @Operation(summary = "getAllPostCommentsById")
    @Secured(value = "ROLE_USER")
    //@PreAuthorize()
    //@PostAuthorize()
    //@PreFilter()
    //@PostFilter()
    public List<PostComment> getAllPostCommentsById(@RequestParam(value = "id", required = false) String id){
        System.out.println("ID : " + id);
        return postCommentService.getAllPostComment().getBody();
    }

    @GetMapping({"/postCommentsById/{id}" , "/postCommentsById/"})
    @Operation(summary = "Get All Post Comment By ID ...")
   // @Secured(value = "ROLE_USER")
    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<PostComment>> getAllPostCommentsById_(@PathVariable Optional<String> id){
        System.out.println("ID : " + id.orElse("no ID"));
        return postCommentService.getAllPostComment();
    }

    @GetMapping("/postCommentsByReview")
    @Operation(summary = "findAllByReview")
    @Secured(value = "ROLE_USER")
    public ResponseEntity<List<PostCommentDto>> findAllByReview(@RequestParam String review) {
        System.out.println("ID:" + review);
        return ResponseEntity.ok(postCommentService.findAllByReview(review));
    }

    @PostMapping("/insertComment")
    @Operation(summary = "insert")
    @Secured(value = "ROLE_USER")
    public ResponseEntity<String> insert(@RequestParam(value = "review") String review){
        postCommentService.insertCommentWithJdbcTemplate(review);
       return new ResponseEntity<>("Inserted", HttpStatus.CREATED);
    }

    @GetMapping("/postCommentsByIdJdbc")
    @Operation(summary = "getAllPostCommentsByIdJdbc")
    @Secured(value = "ROLE_USER")
    public PostComment getAllPostCommentsByIdJdbc(@RequestParam(value = "id", required = false) String id){
        return postCommentService.selectById(id);
    }
    @DeleteMapping("/deleteById")
    public void deleteById(@RequestParam(value = "id") Long id){
        System.out.println("ID : " + id);
        postCommentService.deleteById(id);

    }

    @GetMapping({"postCommentsById/{localDateTime}"})
    public ResponseEntity<PostComment> getByCreationDate(@RequestParam(value = "localDateTime") LocalDateTime localDateTime){
        return postCommentService.selectByCreationDate(localDateTime);
    }
}
