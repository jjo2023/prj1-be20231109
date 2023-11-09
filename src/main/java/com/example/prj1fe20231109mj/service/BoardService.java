package com.example.prj1fe20231109mj.service;

import com.example.prj1fe20231109mj.domain.Board;
import com.example.prj1fe20231109mj.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BoardService {


    private final BoardMapper mapper;

    public void save(Board board) {
        mapper.insert(board);
    }
}
