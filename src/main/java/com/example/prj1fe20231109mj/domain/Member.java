package com.example.prj1fe20231109mj.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private String id;
    private String password;
    private String email;
    private LocalDateTime inserted;
}
