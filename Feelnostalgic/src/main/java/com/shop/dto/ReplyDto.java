package com.shop.dto;

import com.shop.entity.Comment;
import com.shop.entity.Reply;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyDto {
    private Long id;

    private String ReplyWriter;

    private String ReplyContent;

    private Long boardId;

    private Long parentId;


    public static ReplyDto toReplyDtoWriter(Reply reply, Long parentId){
    ReplyDto replyDto = new ReplyDto();
    replyDto.setId(reply.getId());

    replyDto.setReplyWriter(reply.getReplyWriter());
    replyDto.setReplyContent(reply.getReplyContent());
    replyDto.setParentId(parentId);

    return replyDto;
    }
}
