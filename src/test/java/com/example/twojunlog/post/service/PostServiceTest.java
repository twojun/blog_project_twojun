package com.example.twojunlog.post.service;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.dto.request.PostSearchDto;
import com.example.twojunlog.post.dto.response.PostResponseDto;
import com.example.twojunlog.post.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * given : 테스트 검증을 위해 값을 세팅 (테스트에 사용되는 변수나 입력값, Mock 객체 등)
 * when : 테스트 로직
 * then : 테스트 검증 (테스트를 수행함으로써 예상되는 값)
 */
@SpringBootTest
@AutoConfigureMockMvc
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void cleanDb() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 작성")
    void 게시글_작성() throws Exception {
        // Given
        PostCreateDto postCreate = PostCreateDto.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // When
        postService.write(postCreate);

        // Then
        Assertions.assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", post.getTitle());
        Assertions.assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("게시글 단건 조회")
    void 게시글_단건_조회() throws Exception {
        // Given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        Post newPost = postRepository.save(post);

        // When
        PostResponseDto response = postService.get(newPost.getId());

        // Then
        Assertions.assertNotNull(response);
        Post findPost = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", response.getTitle());
        Assertions.assertEquals("내용입니다.", response.getContent());
    }

    @Test
    @DisplayName("게시글 다수 조회")
    void 게시글_다수_조회() throws Exception {
        // Given
        Post post1 = Post.builder()
                .title("제목입니다1.")
                .content("내용입니다1.")
                .build();
        Post newPost1 = postRepository.save(post1);

        Post post2 = Post.builder()
                .title("제목입니다2.")
                .content("내용입니다2.")
                .build();
        Post newPost2 = postRepository.save(post2);

        PostSearchDto postSearchDto = PostSearchDto.builder()
                .page(1)
                .size(10)
                .build();

        // When
        List<PostResponseDto> posts = postService.getList(postSearchDto);

        // Then
        Assertions.assertEquals(2L, posts.size());
    }

    @Test
    @DisplayName("1페이지 조회")
    void 페이지네이션() throws Exception {
        // Given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> {
                    return Post.builder()
                            .title("제목 " + i)
                            .content("wonjun " + i)
                            .build();
                })
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        PostSearchDto postSearchDto = PostSearchDto.builder()
                .page(1)
                .size(10)
                .build();

        // When
        List<PostResponseDto> posts = postService.getList(postSearchDto);

        // Then
        Assertions.assertEquals(10L, posts.size());
        Assertions.assertEquals("제목 19", posts.get(0).getTitle());
    }
}