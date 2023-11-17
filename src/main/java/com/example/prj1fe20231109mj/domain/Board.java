package com.example.prj1fe20231109mj.domain;

import com.example.prj1fe20231109mj.util.AppUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Integer id;
    private String title;
    private String content;
    private String writer;
    private String nickName;
    private LocalDateTime inserted;
    private Integer countComment;
    private Integer countLike;


    public String getAgo() {
        return AppUtil.getAgo(inserted, LocalDateTime.now());
    }
}
