package com.shop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.beans.Transient;
import java.time.LocalDate;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;


    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String passwordConfirm;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String zipcode;

    @NotEmpty(message = "주소는 필수 입력 값입니다.")
    private String street;
    @NotEmpty(message = "상세 주소를 입력해주세요.")
    private String detail;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    private String tel;

    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;

    private int number;

    private int Confirm;


}
