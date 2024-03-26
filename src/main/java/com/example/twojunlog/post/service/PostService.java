package com.example.twojunlog.post.service;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreateDto postCreateDto) {
        postRepository.save(Post.builder()
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .build());
    }

    public Post get(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() ->  new IllegalArgumentException("존재하지 않는 게시글 ID입니다."));
    }
}
