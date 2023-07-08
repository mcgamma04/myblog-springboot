package com.stringcodeltd.myblogapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    @NotEmpty(message = "Nmame should not be null or empty")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @NotEmpty(message = "Comment should not be null or empty")
    @Size(min = 2, message = "Coment should be at least 2 character")
    private String body;
}
