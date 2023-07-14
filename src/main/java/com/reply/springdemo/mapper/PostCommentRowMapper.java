package com.reply.springdemo.mapper;

import com.reply.springdemo.Status;
import com.reply.springdemo.model.PostComment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostCommentRowMapper implements RowMapper<PostComment> {
    @Override
    public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostComment postComment = PostComment.builder().build();

        postComment.setId(rs.getLong("id"));
        postComment.setStatus(Status.valueOf(rs.getString("status")));
        return postComment;
    }
}
