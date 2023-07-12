package com.reply.springdemo;

import com.reply.springdemo.model.PostComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostCommentRepo extends JpaRepository<PostComment, Long> {


    @Query(value = "select comment from PostComment comment")
     List<PostComment> findAllPaginated(Pageable pageable);
}
