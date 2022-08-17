package com.sparta.spring01.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {  // 테이블의 데이터에 접근할 때 완충재 역할
    private Long id;
    private String title;
    private String author;
    private String password;
    private String contents;
}
