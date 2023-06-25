package com.stringcodeltd.myblogapp.repository;

import com.stringcodeltd.myblogapp.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
