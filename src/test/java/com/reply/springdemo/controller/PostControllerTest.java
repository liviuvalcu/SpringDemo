package com.reply.springdemo.controller;

import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.exceptions.BadArgumentException;
import com.reply.springdemo.service.PostCommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static  org.mockito.Mockito.*;
import static  org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static  org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PostCommentService postCommentService;

    @Autowired
    private ApplicationContext context;

    @Test
    void getAllPostComment() throws Exception {
        when(postCommentService.getAllPostComment()).thenReturn(List.of(PostComment.builder().build()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/postComments").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    void getAllPostCommentsById() throws Exception {
        when(postCommentService.getAllPostComment()).thenReturn(List.of(PostComment.builder().build()));
        String [] beans = context.getBeanDefinitionNames();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/postCommentsById")
                        //.param("id", "201")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    void getAllPostCommentsById_ERROR() throws Exception{
        when(postCommentService.getAllPostComment()).thenThrow(BadArgumentException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/posts/postCommentsById").param("id","1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadArgumentException))
                .andDo(print())
                .andReturn();
    }
}