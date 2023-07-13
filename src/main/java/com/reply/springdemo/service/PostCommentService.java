package com.reply.springdemo.service;

import com.reply.springdemo.PostCommentRepo;
import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.Status;
import com.reply.springdemo.repository.PostCommentRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
//@Scope("prototype")
//@Lazy
public class PostCommentService implements InitializingBean {

    @Autowired
    private PostCommentRepo postCommentRepo;

    @PostConstruct
    public void postConstruct(){
        System.out.println("PostCommentService PC");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet PC");
    }

    public void init(){
        System.out.println("init PC");
    }

    @PreDestroy
    public void destroy(){
        System.out.println(" PostCommentService - PD");
    }

    public ResponseEntity<List<PostComment>> getAllPostComment(){
      return new ResponseEntity<>(postCommentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<PostComment>> getAllPostCommentPaginated(Integer page, Integer quantity){
        Sort sort = Sort.by("status").ascending();

        return new ResponseEntity<>(postCommentRepo.findAllPaginated(PageRequest.of(page, quantity, sort)), HttpStatus.OK);
    }


    public List<PostComment> selectByIdPaginated(Long id, Pageable pageable){
        return postCommentRepo.selectByIdPaginated(id, pageable);
    }

    public PostComment selectById(Long id){
        return postCommentRepo.selectById(id);
    }

    public List<PostComment> findByStatus(String status){
        return postCommentRepo.findByStatus(status);
    }

    List<PostComment> findDistinctByVotes( Integer votes){
        return postCommentRepo.findDistinctByVotes(votes);
    }

}
