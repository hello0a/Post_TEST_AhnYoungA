package com.aloha.post.domain;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class Post {
    private Long no;
    private String id;  
    private String title;  
    private String writer;  
    private String content;  
    private Date createdAt;  
    private Date updatedAt;  

    private Post() {
        this.id = UUID.randomUUID().toString();
    }
}
