package com.shop.service;

import com.shop.dto.CommentDto;
import com.shop.entity.Board;
import com.shop.entity.Comment;
import com.shop.repository.BoardRepository;
import com.shop.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public void CommentWirte(Comment comment){
        System.out.println();
        commentRepository.save(comment);
    }


    public List<CommentDto> FindAll(Long BoardId){
        Board board = boardRepository.findById(BoardId).get();
        List<Comment> commentList = commentRepository.findAllByboardOrderById(board);
        List<CommentDto> commentDtoList = new ArrayList<>();
        for(Comment comment : commentList){
            CommentDto commentDto = CommentDto.tocommentDto(comment,BoardId);
            commentDtoList.add(commentDto);
        }
        return commentDtoList;
    }


}
