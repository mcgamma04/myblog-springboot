package com.stringcodeltd.myblogapp.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private long id;
    private String name;
    private String email;
    private String body;
}
