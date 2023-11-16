package com.example.prj1fe20231109mj.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer id;
    private Integer boardId;
    private String memberId;
    private String memberNickName;
    private String comment;
    private LocalDateTime inserted;
}
