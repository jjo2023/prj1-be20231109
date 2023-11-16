package com.example.prj1fe20231109mj.controller;

import com.example.prj1fe20231109mj.domain.Like;
import com.example.prj1fe20231109mj.domain.Member;
import com.example.prj1fe20231109mj.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService service;

    @PostMapping
    public ResponseEntity like(@RequestBody Like like,
                               @SessionAttribute(value = "login", required = false) Member login) {

        if (login == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        service.update(like, login);
        return null;
    }


}
