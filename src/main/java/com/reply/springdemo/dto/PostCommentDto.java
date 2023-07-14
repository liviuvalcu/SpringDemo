package com.reply.springdemo.dto;

import com.reply.springdemo.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDto {
    private String review;
    private Status status;
    private int votes;
}
