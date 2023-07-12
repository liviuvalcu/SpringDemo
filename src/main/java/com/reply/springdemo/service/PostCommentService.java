package com.reply.springdemo.service;

import com.reply.springdemo.PostCommentRepo;
import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.Status;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

}
