package com.example.prj1fe20231109mj.controller;

import com.example.prj1fe20231109mj.domain.Board;
import com.example.prj1fe20231109mj.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService service;

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Board board) {

        if (!service.validate(board))
            return ResponseEntity.badRequest().build();
        if (service.save(board)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();


        }
    }

}













