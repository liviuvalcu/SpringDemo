package com.reply.springdemo.service;

import com.reply.springdemo.Status;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Data
public class PostService {

   // @Autowired
    //@Lazy
    private  PostCommentService postCommentService;

    @Value("${application.name}")
    private String name;

    @Value("#{decidingStatus.getAcceptedValue()}")
    //@Value("#{T(com.reply.springdemo.nonBean.NonBeanClass).showMeSomething()}")
    //@Value("#{systemProperties['']}")
    //@Value("#{systemEnvironment['']}")
    //@Value("#{environment['']}")
    private Status acceptedStatus;

    public PostService(PostCommentService postCommentService){
        this.postCommentService = new PostCommentService();
        this.name = name;
        System.out.println(acceptedStatus);
    }

   // public PostService(){}

    @PreDestroy
    public void destroy(){
        System.out.println(" PostService - PD");
    }
}
