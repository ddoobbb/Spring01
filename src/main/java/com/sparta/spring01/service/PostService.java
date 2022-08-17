package com.sparta.spring01.service;

import com.sparta.spring01.dto.PasswordRequestDto;
import com.sparta.spring01.entity.Post;
import com.sparta.spring01.repository.PostRepository;
import com.sparta.spring01.dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final 선언할때 스프링에게 알려줌
@Service // 스프링에게 이 클래스는 서비스임을 명시
public class PostService {

    private final PostRepository postRepository; // final : 서비스에게 꼭 필요함을 명시

    @Transactional // 업데이트를 할 때, DB에 반영이 되는 것을 스프링에게 알려줌
    public Long update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        // 비밀번호 불일치 시에 게시물 수정 불가
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        post.update(requestDto);
        return post.getId();
    }

    @Transactional
    public Long delete (Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        if (!post.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        postRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Boolean check(Long id, PasswordRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return post.getPassword().equals(requestDto.getPassword());
    }
}
