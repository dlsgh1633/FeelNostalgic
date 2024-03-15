package com.shop.controller;

import com.shop.dto.BoardDto;
import com.shop.entity.Member;
import com.shop.service.BoardService;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;
    private final BoardService boardService;

    // 회원에 세션 정보 가져오려면 Authentication를 쓰자 ! @Authentication은 아니다 !
    @GetMapping(value = "/mypage")
    public String mypage(Authentication authentication, Model model) {
        String email = authentication.getName();
        Member member = memberService.loadUserByEmail(email);

        model.addAttribute("memberdto", member);
        return "mypage/itsme";
    }



    @GetMapping("/memberupdate")
    public String editForm(Authentication authentication, Model model) {
        String email = authentication.getName();
        Member member = memberService.loadUserByEmail(email);
        model.addAttribute("memberForm", member);
        return "mypage/memberupdate";
    }


    @PostMapping("/update")
    public String updateMember(@ModelAttribute("memberForm") Member memberForm, Authentication authentication) {
        String email = authentication.getName();
        memberService.updateMember(email, memberForm);
        return "redirect:/mypage";
    }




}
