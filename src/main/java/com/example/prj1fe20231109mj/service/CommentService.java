package com.example.prj1fe20231109mj.service;

import com.example.prj1fe20231109mj.domain.Comment;
import com.example.prj1fe20231109mj.domain.Member;
import com.example.prj1fe20231109mj.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper mapper;
    public boolean add(Comment comment, Member login) {
        comment.setMemberId(login.getId());
        return mapper.insert(comment)==1;
    }

    public boolean validate(Comment comment){
        if (comment == null){
            return false;
        }

        if (comment.getBoardId()==null||comment.getBoardId()<1){
            return false;
        }
        if (comment.getComment()==null||comment.getComment().isBlank()){
            return false;
        }
        return true;

    }

    public List<Comment> list(Integer boardId) {
        return mapper.selectByBoardId(boardId);
    }
}









