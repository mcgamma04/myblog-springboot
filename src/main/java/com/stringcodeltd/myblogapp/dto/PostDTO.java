package com.stringcodeltd.myblogapp.dto;

import com.stringcodeltd.myblogapp.model.Comment;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Post title should have atleat 3 characters")
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 5, message = "Post description should have atleat 5 characters")
    private String description;
    @NotNull
    @NotEmpty
    @Size(min = 8, message = "Post content should have atleat 8 characters")
    private String content;
    private Set<CommentDTO> comments;
}
