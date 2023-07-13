package com.reply.springdemo.repository;

import com.reply.springdemo.Status;
import com.reply.springdemo.model.PostComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Testcontainers
class PostCommentRepoTest {

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

    @Autowired
    private PostCommentRepo postCommentRepo;

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
        registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
        registry.add("spring.flyway.enabled", () -> "true");
    }


    @Test
    void selectById() {
        insertPostComments();
        List<PostComment> postComments = postCommentRepo.findAllPaginated(Pageable.unpaged());

        assertEquals(2, postComments.size());
    }

    private void insertPostComments(){
        postCommentRepo.save(PostComment.builder().id(1L).review("Test").build());
        postCommentRepo.save(PostComment.builder().id(2L).review("no review").build());
    }
}