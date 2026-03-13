package com.aloha.post.service;

import java.util.List;

import com.aloha.post.dto.Page;
import com.aloha.post.dto.Post;

public interface PostService {
    List<Post> list() throws Exception;
    Post select(Long no) throws Exception;
    boolean insert(Post post) throws Exception;
    boolean update(Post post) throws Exception;
    boolean delete(Long no) throws Exception;
    // 페이지 목록
    List<Post> page(Page page) throws Exception;
}
