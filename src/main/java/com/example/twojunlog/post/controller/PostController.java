package com.example.twojunlog.post.controller;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.dto.request.PostSearchDto;
import com.example.twojunlog.post.dto.response.PostResponseDto;
import com.example.twojunlog.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * GET: /posts : 글 전체 조회(검색 + 페이징)
 * GET: /posts/{postId} : 특정 게시글 단건 조회
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreateDto postCreateDto) {
        postService.write(postCreateDto);
    }

    @GetMapping("/posts/{postId}")
    public PostResponseDto get(@PathVariable Long postId) {
        return postService.get(postId);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getList(@RequestParam PostSearchDto postSearchDto) {
        return postService.getList(postSearchDto);
    }
}
