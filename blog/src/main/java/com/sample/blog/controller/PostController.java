package com.sample.blog.controller;

import com.sample.blog.common.JwtTokenUtil;
import com.sample.blog.domain.Post;
import com.sample.blog.domain.User;
import com.sample.blog.model.ErrorResponse;
import com.sample.blog.model.PostRequest;
import com.sample.blog.model.PostResponse;
import com.sample.blog.repository.PostRepository;
import com.sample.blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private JwtTokenUtil jwtTokenUtil;
    private static final Integer PAGE_SIZE = 10;

    public PostController(UserRepository userRepository, JwtTokenUtil jwtTokenUtil, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
        this.postRepository = postRepository;
    }

    @GetMapping(value = "api/v1/post/top")
    public ResponseEntity<Object> getTop10Post(@RequestParam(value = "page", required = false) Integer page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Post> userPosts = postRepository.findAll(pageable);
        List<PostResponse> postResponses = new ArrayList<>();
        for(Post post: userPosts) {
            User user = userRepository.getOne(post.getUserId());
            postResponses.add(PostResponse.builder().body(post.getBody()).author(user.getUsername()).title(post.getTitle()).build());
        }
        return ResponseEntity.ok(postResponses);
    }

    @GetMapping(value = "api/v1/post/my")
    public ResponseEntity<Object> getMyPost(
            @RequestHeader(value = "token", required = true) String token,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        page = page != null ? page : 0;
        try{
            Jws<Claims> jwtTokenData =  jwtTokenUtil.getTokenData(token);
            List<User> users = userRepository.findByToken(token);
            if(users.size() > 0){
                User user = users.get(0);

                if(jwtTokenData.getBody().get("username").equals(user.getUsername()) &&
                        jwtTokenData.getBody().get("email").equals(user.getEmail())
                ) {
                    Pageable pageable = PageRequest.of(page, PAGE_SIZE);
                    List<Post> userPosts = postRepository.findByUserId(user.getId(), pageable);
                    List<PostResponse> postResponses = new ArrayList<>();
                    for(Post post: userPosts) {
                        postResponses.add(PostResponse.builder().body(post.getBody()).author(user.getUsername()).title(post.getTitle()).build());
                    }
                    return ResponseEntity.ok(postResponses);

                }
            }

        }catch (SignatureException signatureException) {
           System.out.println("JWT token invalid");
        }
        return ResponseEntity.badRequest().body(ErrorResponse.builder().code(400).message("Invalid jwt token").build());
    }

    @PostMapping(value = "/api/v1/post/create")
    public ResponseEntity<Object> login(@RequestBody PostRequest postRequest) {
        Post post = new Post();
        post.setUserId(postRequest.getUserId());
        post.setBody(postRequest.getBody());
        post.setTitle(postRequest.getTitle());
        postRepository.save(post);
        return ResponseEntity.ok("post created");
    }

}
