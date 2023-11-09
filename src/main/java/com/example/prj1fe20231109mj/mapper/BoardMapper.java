package com.example.prj1fe20231109mj.mapper;

import com.example.prj1fe20231109mj.domain.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {
    @Insert("""
INSERT INTO board (title, content, writer)
VALUES (#{title}, #{content}, #{writer})
"""
    )

    int insert(Board board);

}
