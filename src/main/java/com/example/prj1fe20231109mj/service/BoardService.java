package com.example.prj1fe20231109mj.service;

import com.example.prj1fe20231109mj.domain.Board;
import com.example.prj1fe20231109mj.domain.Member;
import com.example.prj1fe20231109mj.mapper.BoardMapper;
import com.example.prj1fe20231109mj.mapper.CommentMapper;
import com.example.prj1fe20231109mj.mapper.FileMapper;
import com.example.prj1fe20231109mj.mapper.LikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper mapper;
    private final CommentMapper commentMapper;
    private final LikeMapper likeMapper;
    private final FileMapper fileMapper;

    public boolean save(Board board, MultipartFile[] files, Member login) {
        //
        board.setWriter(login.getId());

        int cnt = mapper.insert(board);


        if (files != null) {
            for (int i = 0; i < files.length; i++){
        // boardFile 테이블에 files 정보 저장
        // boardId, name
        fileMapper.insert(board.getId(),files[i].getOriginalFilename());

            }

        }





        // 실제 파일을 S3 bucket에 uplaod

        return cnt == 1;
    }

    public boolean validate(Board board) {
        if (board == null) {
            return false;
        }
        if (board.getContent() == null || board.getContent().isBlank()) {
            return false;
        }
        if (board.getTitle() == null || board.getTitle().isBlank()) {
            return false;
        }

        return true;
    }

    public Map<String, Object> list(Integer page, String keyword) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> pageInfo = new HashMap<>();

        // int countAll = mapper.countAll();
        int countAll = mapper.countAll("%"+ keyword +"%"); // %%
        int lastPageNumber = (countAll -1) / 10 + 1;
        int startPageNumber = (page -1) / 10 * 10 + 1;
        int endPageNumber = startPageNumber + 9;
        endPageNumber = Math.min(endPageNumber,lastPageNumber);
        int prevPageNumber = startPageNumber -10;
        int nextPageNumber = endPageNumber +1;

        pageInfo.put("currentPageNumber", page);
        pageInfo.put("startPageNumber", startPageNumber);
        pageInfo.put("endPageNumber", endPageNumber);
        if (prevPageNumber > 0){
            pageInfo.put("prevPageNumber", prevPageNumber);
        }
        if (nextPageNumber <= lastPageNumber) {

            pageInfo.put("nextPageNumber",nextPageNumber);
        }



        pageInfo.put("prevPageNumber", startPageNumber - 10);
        pageInfo.put("nextPageNumber", 0);

        int from = (page - 1) * 10;
        map.put("boardList", mapper.selectAll(from,"%"+keyword+"%"));
        map.put("pageInfo", pageInfo);
        return map;
    }



    public Board get(Integer id) {
        return mapper.selectById(id);
    }

    public boolean remove(Integer id) {
        // 게시물에 달린 댓글들 지우기
        commentMapper.deleteByBoardId(id);

        // 좋아요 테이블 지우기
        likeMapper.deleteByBoardId(id);

        return mapper.deleteById(id) == 1;
    }

    public boolean update(Board board) {
        return mapper.update(board) == 1;

    }

    public boolean hasAccess(Integer id, Member login) {
        if (login == null) {
            return false;
        }
        if (login.isAdmin()) {
            return true;
        }
        Board board = mapper.selectById(id);


        return board.getWriter().equals(login.getId());
    }


}
