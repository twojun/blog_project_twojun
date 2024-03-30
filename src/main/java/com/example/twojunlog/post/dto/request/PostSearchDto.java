package com.example.twojunlog.post.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class PostSearchDto {

    private Integer page = 1;
    private Integer size = 20;

    @Builder
    public PostSearchDto(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }
}
