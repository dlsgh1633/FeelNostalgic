package com.shop.entity;

import com.shop.dto.AcBoardDto;
import com.shop.dto.BoardDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class AcBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private String imgName;
    private String oriImgName;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public static AcBoard writeBoard(AcBoardDto acboardDto, Member member) {
        AcBoard acboard = new AcBoard();
        acboard.setName(member.getName());
        acboard.setTitle(acboardDto.getTitle());
        acboard.setContent(acboardDto.getContent());
        return acboard;
    }


    public void updateAcBoardImg(String oriImgName, String imgName, String imgUrl) {
        this.oriImgName = oriImgName;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
    }


}
