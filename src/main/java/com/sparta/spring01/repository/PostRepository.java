package com.sparta.spring01.repository;

import com.sparta.spring01.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
    // 모두 불러와 id에 대하여 내림차순 정렬
}
