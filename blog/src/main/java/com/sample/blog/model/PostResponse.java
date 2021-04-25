package com.sample.blog.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostResponse {
    String title;
    String body;
    String author;
}
