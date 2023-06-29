package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long postId, CommentDTO commentDTO);
    List<CommentDTO> getCommentByPostId(long postId);

    CommentDTO getCommentById(long commentId,long postId);

   CommentDTO updateComment(long commentid, CommentDTO commentDTO);

}
