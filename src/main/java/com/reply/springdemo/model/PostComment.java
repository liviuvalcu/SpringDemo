package com.reply.springdemo.model;

import com.reply.springdemo.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "POST_COMMENT")
@Entity
public class PostComment {


    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private PostComment parent;

    private String review;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime creationDate;
    private int votes;
}
