package com.shop.service;

import com.shop.dto.BoardDto;
import com.shop.dto.SessionUser;
import com.shop.entity.Board;
import com.shop.entity.Member;
import com.shop.repository.BoardRepository;
import com.shop.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    @Value("${boardImgLocation}")
    private String boardImgLocation;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileService fileService;
    private final HttpService httpService;

    public List<Board> boardlist() {
        return boardRepository.findAll();
    }

    public void writeBoard(Board board, MultipartFile boardImgFile) throws Exception {
        String oriImgName = boardImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(boardImgLocation, oriImgName, boardImgFile.getBytes());
            imgUrl = "/images/board/" + imgName;
            board.updateBoardImg(oriImgName, imgName, imgUrl);
        }


        boardRepository.save(board);
    }


    public Board viewboard(Long id) {

        return boardRepository.findById(id).get();
    }


    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    public Member findMembername(Principal principal) {
        String prinEmail = httpService.principalEmail(principal);
        return memberRepository.findByEmail(prinEmail).orElseThrow();
    }



    public BoardDto findById(Long id) {
        Optional<Board> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            Board boardEntity = optionalBoardEntity.get();
            BoardDto boardDTO = BoardDto.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }


}
