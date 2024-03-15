package com.shop.entity;

import com.shop.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String password;
    @Transient
    @Column(nullable = false)
    private String passwordConfirm;

    private String imgName;
    private String oriImgName;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    // cascade = CascadeType.ALL, orphanRemoval = true 이란것은 부모엔티티의 변경이 자식 엔티티에도 전파되는 느낌.
    private List<Comment> commentList = new ArrayList<>();


    public static Board writeBoard(BoardDto boardDto, PasswordEncoder passwordEncoder, Member member) {
        Board board = new Board();
        board.setName(member.getName());
        board.setTitle(boardDto.getTitle());
        String password = passwordEncoder.encode(boardDto.getPassword());
        board.setPassword(password);
        board.setContent(boardDto.getContent());
        return board;
    }


    public void updateBoardImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }


}
