package com.aloha.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.post.dto.Page;
import com.aloha.post.dto.Post;

@Mapper
public interface PostMapper {
    List<Post> list();
    Post select(Long no);
    int insert(Post post);
    int update(Post post);
    int delete(Long no);
    // 페이지 목록
    List<Post> page(Page page);
    long count();
}
