package com.sample.blog.controller;

import com.sample.blog.common.JwtTokenUtil;
import com.sample.blog.domain.User;
import com.sample.blog.model.ErrorResponse;
import com.sample.blog.model.UserLoginRequest;
import com.sample.blog.model.UserRegisterRequest;
import com.sample.blog.model.UserResponse;
import com.sample.blog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.SignatureException;
import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    public UserController(UserRepository userRepository, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/api/v1/user/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest userLoginRequest){
        List<User> users = userRepository.findByEmail(userLoginRequest.getEmail());
        if(users.size() > 0){
            User user = users.get(0);
            if(user.getPassword().equals(userLoginRequest.getPassword())){
                try{
                    Jws<Claims> jwtTokenData =  jwtTokenUtil.getTokenData(user.getToken());
                    if(!jwtTokenData.getBody().get("username").equals(user.getUsername()) ||
                            !jwtTokenData.getBody().get("email").equals(user.getEmail())
                    ) {
                        return ResponseEntity.badRequest().body(ErrorResponse.builder().code(400).message("Invalid jwt token").build());
                    }
                }catch (SignatureException signatureException){
                    System.out.println("Jwt token not found");
                    String token = jwtTokenUtil.createToken(user.getUsername(), user.getEmail());
                    user.setToken(token);
                    userRepository.save(user);
                }
                return ResponseEntity.ok(
                        UserResponse.builder().id(String.valueOf(user.getId())).username(user.getUsername()).token(user.getToken()).build()
                );
            }
        }
        return ResponseEntity.badRequest().body(ErrorResponse.builder().code(400).message("User Not found").build());
    }

    @PostMapping(value = "/api/v1/user/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterRequest userRegisterRequest){
        List<User> users = userRepository.findByEmail(userRegisterRequest.getEmail());
        if(users.size() > 0){
            return ResponseEntity.badRequest().body(ErrorResponse.builder().code(400).message("uUser Already exist").build());
        }

        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setEmail(userRegisterRequest.getEmail());
        String token = jwtTokenUtil.createToken(user.getUsername(), user.getEmail());
        user.setToken(token);
        userRepository.save(user);
        return ResponseEntity.ok(
                UserResponse.builder().id(String.valueOf(user.getId())).username(user.getUsername()).token(user.getToken()).build()
        );
    }
}
