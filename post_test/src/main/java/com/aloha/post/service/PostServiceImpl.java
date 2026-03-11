package com.aloha.post.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.post.domain.Page;
import com.aloha.post.domain.Post;
import com.aloha.post.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    
    private final PostMapper postMapper;

    @Override
    public List<Post> list() throws Exception {
        return postMapper.list();
    }

    @Override
    public Post select(Long no) throws Exception {
        return postMapper.select(no);
    }

    @Override
    public boolean insert(Post post) throws Exception {
        int result = postMapper.insert(post);
        return result > 0;
    }

    @Override
    public boolean update(Post post) throws Exception {
        int result = postMapper.update(post);
        return result > 0;
    }

    @Override
    public boolean delete(Long no) throws Exception {
        int result = postMapper.delete(no);
        return result > 0;
    }

    @Override
    public List<Post> page(Page page) throws Exception{
        long total = postMapper.count();
        page.setTotal(total);
        List<Post> list = postMapper.page(page);
        return list;
    }
}
