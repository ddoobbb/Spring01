package com.sparta.spring01.controller;

import com.sparta.spring01.dto.PasswordRequestDto;
import com.sparta.spring01.entity.Post;
import com.sparta.spring01.repository.PostRepository;
import com.sparta.spring01.dto.PostRequestDto;
import com.sparta.spring01.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;



    // 전체 게시글 목록 조회
    @GetMapping("/api/posts")
    public List<Post> getPosts() {
        return postRepository.findAllByOrderByModifiedAtDesc();
    }


    //게시글 작성
    @PostMapping("/api/posts")
    public Post writePost(@RequestBody PostRequestDto requestDto){
        Post post = new Post(requestDto);
        return postRepository.save(post);
    }

    //비밀번호 확인
    @PostMapping("/api/posts/{id}")
    public Boolean CheckPassword(@PathVariable Long id, @RequestBody PasswordRequestDto requestDto) {
        return postService.check (id, requestDto);
    }

    // 개별 게시글 조회
    @GetMapping("/api/posts/{id}")
    public Optional<Post> getPosts(@PathVariable Long id) {
        return postRepository.findById(id);
    }

    // 게시글 수정
    @PutMapping("/api/posts/{id}")
    public Long updatePost(@PathVariable Long id,@RequestBody PostRequestDto requestDto){
        postService.update(id, requestDto);
        return id;
    }

    // 게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public Long deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return id;
    }




}


