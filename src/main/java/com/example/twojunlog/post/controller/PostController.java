package com.example.twojunlog.post.controller;

import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreateDto postCreateDto) {
        postService.write(postCreateDto);
        return Map.of();
    }

    @GetMapping("/posts")
    public String get() {
        return "Hello World!";
    }
}
