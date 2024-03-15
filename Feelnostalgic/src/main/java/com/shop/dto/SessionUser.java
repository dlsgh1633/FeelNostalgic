package com.shop.dto;

import com.shop.entity.Member;
import com.shop.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class SessionUser implements Serializable{ // Serializable -> 직렬화 이 인터페이스는 마킹인터페이스.
    private String name;
    private String email;


    public SessionUser(Member member){
        this.name = member.getName();
        this.email = member.getEmail();

    }
}
