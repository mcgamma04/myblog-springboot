package com.stringcodeltd.myblogapp.dto;

import java.util.List;

public class PostResponse {
    private List<PostDTO> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;


}
