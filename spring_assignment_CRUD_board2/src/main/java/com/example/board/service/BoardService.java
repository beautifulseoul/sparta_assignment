//메서드가 들어가는 파일

package com.example.board.service;

import com.example.board.dto.BoardRequestDto;
import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.ResponseDto;
import com.example.board.entity.Board;
import com.example.board.entity.User;
import com.example.board.jwt.JwtUtil;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;                 // 싱글톤이어야하기 때문!
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //포스팅 게시
    public BoardResponseDto saveBoard(BoardRequestDto boardRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 포스트 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 요청받은 DTO로 DB에 저장할 객체 만들기
            Board board = boardRepository.save(new Board(boardRequestDto, user.getId(), user.getUsername()));        //데이터베이스에 연결해서 저장하려면 @Entity어노테이션이 걸려져있는 Board클래스를 인스턴스로 만들어서 그 값을사용해서 저장해야 함. 그렇기때문에 board 객체를 만들어주고 생성자를 사용해서 값들을 넣어줘야 함.

            return new BoardResponseDto(board);
        } else {
            return null;
        }
    }

    public List<BoardResponseDto> getBoards() {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        List<BoardResponseDto> boardResponseDto = new ArrayList<>();
        for (Board board : boardList) {
            BoardResponseDto boardResponseDto1 = new BoardResponseDto(board);
            boardResponseDto.add(boardResponseDto1);
        }
        return boardResponseDto; //정상 시간출력
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 글입니다")
        );
        return new BoardResponseDto(board);
    }

    @Transactional
    public BoardResponseDto putBoard(Long id, BoardRequestDto boardRequestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        BoardResponseDto boardResponseDto = new BoardResponseDto();

        // 토큰이 있는 경우에만 삭제 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new RuntimeException("삭제 권한이 없습니다.")
            );

            boardResponseDto = new BoardResponseDto(board);
            board.update(boardRequestDto);
        }
        return boardResponseDto;
    }

    public ResponseDto deleteBoard(Long id, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        ResponseDto responseDto = new ResponseDto();

        // 토큰이 있는 경우에만 삭제 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new RuntimeException("존재하지 않는 글입니다")
            );

            boardRepository.delete(board);
            responseDto.setMsg("삭제에 성공하였습니다.");
        }
        return responseDto;
    }

}

