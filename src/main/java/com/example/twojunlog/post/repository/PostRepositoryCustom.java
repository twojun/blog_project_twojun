package com.example.twojunlog.post.repository;

import com.example.twojunlog.post.domain.Post;
import com.example.twojunlog.post.dto.request.PostSearchDto;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearchDto postSearchDto);

    List<Post> getList2(PostSearchDto postSearchDto);
}
