package com.shop.entity;

import com.nimbusds.openid.connect.sdk.claims.Address;
import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;


@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    @Transient
    private String passwordConfirm;

    private String zipcode;
    private String street;
    private String detail;

    private LocalDate birthday;

    private String tel;
    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean emailVerified = false;





    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setTel(memberFormDto.getTel());
        member.setBirthday(memberFormDto.getBirthday());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        member.setZipcode(memberFormDto.getZipcode());
        member.setStreet(memberFormDto.getStreet());
        member.setDetail(memberFormDto.getDetail());

        return member;
    }

    public Member() {

    }


    public Member(String name, String email){
        this.name = name;
        this.email = email;

    }
    public Member update(String name){
        this.name = name;

        return this;
    }



}
