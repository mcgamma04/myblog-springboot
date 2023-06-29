package com.stringcodeltd.myblogapp.service.impl;

import com.stringcodeltd.myblogapp.dto.CommentDTO;
import com.stringcodeltd.myblogapp.exception.BlogApiException;
import com.stringcodeltd.myblogapp.exception.ResourceNotFoundException;
import com.stringcodeltd.myblogapp.model.Comment;
import com.stringcodeltd.myblogapp.model.Post;
import com.stringcodeltd.myblogapp.repository.CommentRepository;
import com.stringcodeltd.myblogapp.repository.PostRepository;
import com.stringcodeltd.myblogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
      Comment comment = mapToEntity(commentDTO);
      //retrieve post Id
        Post post =  postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId));
        //set postid
        comment.setPost(post);
        Comment commentResponse = commentRepository.save(comment);

        return mapToDTO(commentResponse);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDTO getCommentById(long commentId,long postId) {
//        get comment id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("commentId","comment",commentId));
//get POST id
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post",postId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment doest not belong to post");
        }
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postid, long commentid, CommentDTO commentDTO) {
        //find commentId
        Comment comment = commentRepository.findById(commentid).orElseThrow(() -> new ResourceNotFoundException("comment id", "id", commentid));
        //retrieve postid

        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("Post id", "Post", postid));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post ");
        }
       comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());

        Comment commentResponseUpdate = commentRepository.save(comment);
        return mapToDTO(commentResponseUpdate);
    }

    @Override
    public void deletCommentByPostId(long postid, long commentid) {
        //retrieve Post;
        Post post = postRepository.findById(postid).orElseThrow(() -> new ResourceNotFoundException("post", "post id", postid));
        //retrieve comment
       Comment comment =  commentRepository.findById(commentid).orElseThrow(()->new ResourceNotFoundException("comment ","comment id",commentid));
    if(!comment.getPost().getId().equals(post.getId())){
      throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does belong to id");
    }
        commentRepository.delete(comment);

    }

    //map entity to dto

    private  CommentDTO mapToDTO(Comment comment){
        CommentDTO commentDTO =  new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setName(comment.getName());
        commentDTO.setEmail(comment.getEmail());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }

    //map dto to entity
    private Comment mapToEntity(CommentDTO commentDTO){
        Comment comment =  new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;
    }
}
