package com.shop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class CommentDto {
    private Long id;

    private String CommentWriter;

    private String CommentContent;


    private Long ParentsId;

    private Long boardId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime commentCreatedTime;

    public static CommentDto tocommentDto(Comment comment, Long boardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setCommentWriter(comment.getCommentWriter());
        commentDto.setCommentContent(comment.getCommentContent());
        commentDto.setCommentCreatedTime(comment.getRegTime());
        commentDto.setBoardId(boardId);

        return commentDto;
    }

}
