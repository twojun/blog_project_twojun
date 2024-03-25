package com.example.twojunlog.post.service;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreateDto postCreateDto) {
        postRepository.save(new Post(postCreateDto.getTitle(), postCreateDto.getContent()));
    }

}
