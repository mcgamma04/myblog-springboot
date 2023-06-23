package com.stringcodeltd.myblogapp.service.impl;

import com.stringcodeltd.myblogapp.dto.PostDTO;
import com.stringcodeltd.myblogapp.exception.ResourceNotFoundException;
import com.stringcodeltd.myblogapp.model.Post;
import com.stringcodeltd.myblogapp.repository.PostRepository;
import com.stringcodeltd.myblogapp.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        //convert DTO to entity
        Post post  = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

//        Post post =  mapToEntity(postDto);

        Post newPost =  postRepository.save(post);

        //convert entity to dto
//        PostDTO postResponse = mapToDTO(newPost);
        PostDTO postResponse = new PostDTO();

        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setContent(newPost.getContent());
        postResponse.setDescription(newPost.getDescription());
        return postResponse;
    }

    @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostById(long id) {
       Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",id));
       return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post editedPost = postRepository.save(post);

        return mapToDTO(editedPost);
    }

    @Override
    public String deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
        return "Post deleted successfully";
    }

    //convert entity to PostDTO
    private PostDTO mapToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setDescription(post.getDescription());
        return postDTO;
    }
    //convert DTO to entity
    private Post mapToEntity(PostDTO postDto){
        Post post  = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
