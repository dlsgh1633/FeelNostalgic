package com.shop.controller;

import com.shop.dto.CommentDto;
import com.shop.entity.Comment;
import com.shop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Transactional
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/save")
    public @ResponseBody ResponseEntity WriteComment(@ModelAttribute CommentDto commentDto) {
        Long boardId = commentDto.getBoardId();
        Comment comment = Comment.commentWrite(commentDto, boardId);
        commentService.CommentWirte(comment);
        List<CommentDto> commentList = commentService.FindAll(boardId);
        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }


    @GetMapping("/getByBoardId/{boardId}")
    public ResponseEntity<List<CommentDto>> getCommentsByBoardId(@PathVariable Long boardId) {
        List<CommentDto> commentDTOList = commentService.FindAll(boardId);

        return ResponseEntity.ok(commentDTOList);
    }
}
