package com.reply.springdemo;

import com.reply.springdemo.model.PostComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepo extends JpaRepository<PostComment, Long> {


     @Query(value = "select comment from PostComment comment")
     List<PostComment> findAllPaginated(Pageable pageable);

     List<PostComment> findByStatus(@Param("status") String status);

     @Query(value = "select * from POST_COMMENTS where id = :id" , nativeQuery = true)
     PostComment selectById(@Param("id") Long id);

     @Query(value = "select * from POST_COMMENTS where id = :id" , nativeQuery = true, countQuery = "select count(id) from POST_COMMENTS where id = :id")
     List<PostComment> selectByIdPaginated(@Param("id") Long id, Pageable pageable);

     List<PostComment> findDistinctByVotes(@Param("votes") Integer votes);

     @Query(value = "SELECT * FROM POST_COMMENTS WHERE review LIKE %:review%", nativeQuery = true)
     List<PostComment> findAllByReview(@Param("review") String review);
}
