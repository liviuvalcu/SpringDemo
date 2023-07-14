package com.reply.springdemo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reply.springdemo.dto.PostCommentDto;
import com.reply.springdemo.exceptions.BadArgumentException;
import com.reply.springdemo.mapper.PostCommentRowMapper;
import com.reply.springdemo.model.PostComment;
import com.reply.springdemo.Status;
import com.reply.springdemo.repository.PostCommentRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Scope("prototype")
//@Lazy
public class PostCommentService implements InitializingBean {

    @Autowired
    private PostCommentRepo postCommentRepo;

    @Autowired
    private ModelMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostCommentService PC");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet PC");
    }

    public void init() {
        System.out.println("init PC");
    }

    @PreDestroy
    public void destroy() {
        System.out.println(" PostCommentService - PD");
    }

    public ResponseEntity<List<PostComment>> getAllPostComment() {
        return new ResponseEntity<>(postCommentRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<PostComment>> getAllPostCommentPaginated(Integer page, Integer quantity) {
        Sort sort = Sort.by("status").ascending();

        return new ResponseEntity<>(postCommentRepo.findAllPaginated(PageRequest.of(page, quantity, sort)), HttpStatus.OK);
    }


    public ResponseEntity<List<PostComment>> selectByIdPaginated(Long id, Integer page, Integer quantity) {

        Sort sort = Sort.by("status").ascending();

        return new ResponseEntity<>(postCommentRepo.selectByIdPaginated(id,PageRequest.of(page, quantity, sort)), HttpStatus.OK);

    }

    public ResponseEntity<PostComment> selectById(Long id) {
        return new ResponseEntity<>(postCommentRepo.selectById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<PostComment>> findByStatus(String status) {
        return new ResponseEntity<>(postCommentRepo.findByStatus(status), HttpStatus.OK);
    }

    public ResponseEntity<List<PostComment>> findDistinctByVotes(Integer votes) {

        return new ResponseEntity<>(postCommentRepo.findDistinctByVotes(votes),HttpStatus.OK);
    }

    public List<PostCommentDto> findAllByReview(String review) {
        List<PostComment> comments =  postCommentRepo.findAllByReview(review);
       // List<PostCommentDto> commentDtos = new ArrayList<>();
/*
        comments.stream().forEach(comment ->{
            PostCommentDto dto = objectMapper.map(comment, PostCommentDto.class);
            commentDtos.add(dto);
        });
*/
        List<PostCommentDto> commentDtos =  comments.stream().map(comment-> objectMapper.map(comment, PostCommentDto.class)).collect(Collectors.toList());

        return commentDtos;
    }

    @Transactional
    public void insertComment(String review){
        insertComment_(review);
        postCommentRepo.saveAndFlush(PostComment.builder()
                        .id(postCommentRepo.getMaxId() + 1)
                        .review(review)
                        .creationDate(LocalDateTime.now())
                        .status(Status.IN_REVIEW)
                        .votes(0)
                .build());

        throw new BadArgumentException("");
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertComment_(String review){
        postCommentRepo.saveAndFlush(PostComment.builder()
                .id(postCommentRepo.getMaxId() + 1)
                .review(review)
                .creationDate(LocalDateTime.now())
                .status(Status.IN_REVIEW)
                .votes(0)
                .build());
    }

    public void insertCommentWithJdbcTemplate(String review){
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO POST_COMMENT VALUES (?,?,?,?,?,?)");

                preparedStatement.setLong(1, postCommentRepo.getMaxId() + 1);
                preparedStatement.setObject(2, null);
                preparedStatement.setString(3, review);
                preparedStatement.setString(4, Status.REJECTED.name());
                preparedStatement.setDate(5, java.sql.Date.valueOf(LocalDateTime.now().toLocalDate()));
                preparedStatement.setObject(6, 0);
                return preparedStatement;
            }
        });
    }

    public  PostComment selectById(String id){
        String query = "Select * from POST_COMMENT where id = ?";

        return  jdbcTemplate.queryForObject(query, new Object []{id}, new PostCommentRowMapper());
    }
}
