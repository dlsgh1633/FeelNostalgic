package com.shop.service;

import com.shop.dto.CommentDto;
import com.shop.dto.ReplyDto;
import com.shop.entity.Board;
import com.shop.entity.Comment;
import com.shop.entity.Reply;
import com.shop.repository.CommentRepository;
import com.shop.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReplyService {
    private final CommentRepository commentRepository;
    private final ReplyRepository replyRepository;

    public void saveReply(Reply reply){

            replyRepository.save(reply);

    }

    public List<ReplyDto> FindAll(Long commentId){
        System.out.println("commentId는?" + commentId);
        Comment comment = commentRepository.findById(commentId).get();
        List<Reply> replyList = replyRepository.findAllByparentIdOrderById(comment);
        List<ReplyDto> replyDtoList = new ArrayList<>();
        for(Reply reply : replyList){
            ReplyDto replyDto = ReplyDto.toReplyDtoWriter(reply,commentId);
            replyDtoList.add(replyDto);
        }
        return replyDtoList;
    }

}
