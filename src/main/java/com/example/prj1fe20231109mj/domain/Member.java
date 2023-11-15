package com.example.prj1fe20231109mj.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Member {
    private String id;
    private String password;
    private String email;
    private LocalDateTime inserted;
    private String nickName;
    private List<Auth> auth;
}
