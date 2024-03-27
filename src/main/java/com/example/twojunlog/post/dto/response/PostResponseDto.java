package com.example.twojunlog.post.dto.response;

import com.example.twojunlog.post.domain.Post;
import lombok.Builder;
import lombok.Data;

/**
 * 서비스 정책에 상응하는 별도의 응답 전용 DTO
 */
@Data
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;

    // 생성자 오버로딩
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
