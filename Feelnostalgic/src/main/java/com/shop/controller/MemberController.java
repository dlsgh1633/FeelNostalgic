package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MailService;
import com.shop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.NoSuchElementException;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping(value = "/new")
    public String memberForm(@Valid MemberFormDto memberFormDto,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            return "member/memberForm";
        }

        if (!memberFormDto.getPassword().equals(memberFormDto.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm", "passwordConfirm.noMatch", "비밀번호가 일치하지 않습니다.");
        }


        Member member = Member.createMember(memberFormDto, passwordEncoder);
        try {
            memberService.validateDuplicateMember(member);
        } catch (NoSuchElementException e) {

            memberService.saveMember(member);
            return "member/memberForm";
        }
        model.addAttribute("errorMessage", "1");

        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginMember() {
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/logintry")
    public @ResponseBody ResponseEntity<?> logintry() {

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
