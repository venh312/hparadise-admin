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
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String memberLoginForm() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String memberJoinForm() {
        return "member/signup";
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

    @GetMapping("/list")
    public String memberListPage() {
        return "member/list";
    }

    @GetMapping("/detail")
    public String memberDetailPage() {
        return "member/detail";
    }

    @PostMapping("/info/modify")
    public String memberModify() {
        return "jsonView";
    }

    @PostMapping("/find/id")
    public String memberFindId() {
        return "jsonView";
    }

    @PostMapping("/find/password")
    public String memberFindPassword() {
        return "jsonView";
    }

    @PostMapping("/delete")
    public String memberDelete() {
        return "jsonView";
    }
}
