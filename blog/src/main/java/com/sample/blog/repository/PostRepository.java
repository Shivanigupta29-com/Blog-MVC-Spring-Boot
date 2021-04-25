package com.sample.blog.repository;

import com.sample.blog.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
    List<Post> findByUserId(int userId, Pageable pageable);
}