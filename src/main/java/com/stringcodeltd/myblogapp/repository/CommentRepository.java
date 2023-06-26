package com.stringcodeltd.myblogapp.repository;

import com.stringcodeltd.myblogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPostId(long postid);

}
