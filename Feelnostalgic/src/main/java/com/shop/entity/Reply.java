package com.shop.entity;

import com.shop.dto.ReplyDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Reply extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String ReplyWriter;
    private String ReplyContent;



    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment parentId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board parentBoard;

    public static Reply toReplyWrite(ReplyDto replyDto, Long commentId, Long boardId){
        Reply reply = new Reply();
        reply.setId(replyDto.getId());
        reply.setReplyWriter(replyDto.getReplyWriter());
        reply.setReplyContent(replyDto.getReplyContent());

        Comment comment = new Comment();
        comment.setId(commentId);
        reply.setParentId(comment);

        Board board = new Board();
        board.setId(boardId);
        reply.setParentBoard(board);

        return reply;
    }


}
