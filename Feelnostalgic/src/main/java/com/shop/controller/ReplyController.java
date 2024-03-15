package com.shop.controller;

import com.shop.dto.CommentDto;
import com.shop.dto.ReplyDto;
import com.shop.entity.Comment;
import com.shop.entity.Reply;
import com.shop.service.CommentService;
import com.shop.service.ReplyService;
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
public class ReplyController {
    private final ReplyService replyService;
    private final CommentService commentService;

    @PostMapping(value = "/savereply")
    public @ResponseBody ResponseEntity WriteReplyComment(@ModelAttribute ReplyDto replyDto, Long commentId, Long boardId) {
        System.out.println("ReplyDto는? " + replyDto.getReplyContent());
        System.out.println("ReplyDto는? " + replyDto.getReplyWriter());
        System.out.println("commentdto의 id는?" + commentId + "boardid는?" + boardId);

        Reply reply = Reply.toReplyWrite(replyDto, commentId, boardId);
        replyService.saveReply(reply);
        List<ReplyDto> replyList = replyService.FindAll(commentId);
        Comment comment = new Comment();

        return new ResponseEntity<>(replyList, HttpStatus.OK);
    }

    @GetMapping("/getByCommentId/{commentId}")
    public ResponseEntity<List<ReplyDto>> getCommentsByBoardId(@PathVariable Long commentId) {
        List<ReplyDto> replyDtoList = replyService.FindAll(commentId);
        System.out.println("commentId컨트롤러 값" + commentId);
        return ResponseEntity.ok(replyDtoList);
    }


}
