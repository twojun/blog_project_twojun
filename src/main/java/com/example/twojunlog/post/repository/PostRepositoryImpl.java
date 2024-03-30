package com.example.twojunlog.post.repository;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.domain.QPost;
import com.example.twojunlog.post.dto.request.PostSearchDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.twojunlog.post.domain.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getList(PostSearchDto postSearchDto) {
        return queryFactory
                .select(post)
                .from(post)
                .limit(postSearchDto.getSize())
                .offset((long) (postSearchDto.getPage() - 1) * postSearchDto.getSize())
                .orderBy(post.id.desc())
                .fetch();
    }
}
