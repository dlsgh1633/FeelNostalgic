package com.shop.entity;

import com.shop.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "comment")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    private String CommentWriter;

    private String CommentContent;

    private Long ParentsId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public static Comment commentWrite(CommentDto commentDto, Long boardId){
        Comment comment = new Comment();
        comment.id = commentDto.getId();
        comment.CommentWriter = commentDto.getCommentWriter();
        comment.CommentContent = commentDto.getCommentContent();

        Board board = new Board();
        board.setId(boardId);
        comment.setBoard(board);
        return comment;
    }


}
