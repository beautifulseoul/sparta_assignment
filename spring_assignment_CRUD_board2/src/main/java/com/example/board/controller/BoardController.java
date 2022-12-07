package com.example.board.controller;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.ResponseDto;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //포스팅
    @PostMapping("/boards")
    public BoardResponseDto saveBoard(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {     //객체형식으로 넘어오기 때문에 requestbody씀
        return boardService.saveBoard(boardRequestDto, request);
    }

    //모든 포스팅가져오기
    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    //
    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/boards/{id}")
    public BoardResponseDto putBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.putBoard(id, boardRequestDto, request);
    }

    @DeleteMapping("/boards/{id}")
    public ResponseDto deleteBoard(@PathVariable Long id, HttpServletRequest request){
        return boardService.deleteBoard(id, request);
    }
}
