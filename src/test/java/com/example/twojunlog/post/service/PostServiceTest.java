package com.example.twojunlog.post.service;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostCreateDto;
import com.example.twojunlog.post.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

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
    @DisplayName("글 단건 조회")
    void 게시글_단건_조회() throws Exception {
        // Given
        Post post = Post.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();
        Post newPost = postRepository.save(post);

        // When
        postService.get(newPost.getId());

        // Then
        Assertions.assertNotNull(newPost);
        Post findPost = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다.", findPost.getTitle());
        Assertions.assertEquals("내용입니다.", findPost.getContent());
    }
}