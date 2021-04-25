package com.sample.blog.model;

import lombok.Data;

@Data
public class PostRequest {
    String title;
    String body;
    Integer userId;
}
