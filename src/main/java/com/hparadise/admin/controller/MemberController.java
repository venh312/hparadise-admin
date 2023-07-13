package com.hparadise.admin.controller;

import com.hparadise.admin.dto.member.MemberSaveRequest;
import com.hparadise.admin.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String memberLoginForm() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String memberSignupForm() {
        return "member/signup";
    }

    @GetMapping("/forgot-password")
    public String memberForgotPasswordForm() {
        return "member/forgot-password";
    }

    @PostMapping("/save")
    public String memberSave(Model model, @RequestBody MemberSaveRequest request) {
        long result = 0;
        try {
            result = memberService.save(request);
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }

    @PostMapping("/forgot-action")
    public String memberForgot(Model model, @RequestBody MemberSaveRequest request) {
        long result = 0;
        try {
            result = memberService.countByEmail(request.getEmail());
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }

    @GetMapping("/member/list")
    public String memberListPage() {
        return "member/list";
    }

    @GetMapping("/member/detail")
    public String memberDetailPage() {
        return "member/detail";
    }

    @PostMapping("/member/info/modify")
    public String memberModify() {
        return "jsonView";
    }

    @PostMapping("/member/find/id")
    public String memberFindId() {
        return "jsonView";
    }

    @PostMapping("/member/find/password")
    public String memberFindPassword() {
        return "jsonView";
    }

    @PostMapping("/member/delete")
    public String memberDelete() {
        return "jsonView";
    }
}
