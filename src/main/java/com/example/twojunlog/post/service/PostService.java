package com.example.twojunlog.post.service;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.dto.response.PostResponseDto;
import com.example.twojunlog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostResponseDto get(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 ID입니다."));

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponseDto> getList(Pageable pageable) {
        return postRepository.findAll(pageable).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
