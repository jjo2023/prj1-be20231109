package com.example.prj1fe20231109mj.mapper;

import com.example.prj1fe20231109mj.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    @Insert("""
INSERT INTO comment(boardId, comment, memberId )
VALUES (#{boardId}, #{comment}, #{memberId})
""")
    int insert(Comment comment);
}
