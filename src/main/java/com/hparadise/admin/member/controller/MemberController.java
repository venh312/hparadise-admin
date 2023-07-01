package com.hparadise.admin.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/join/form")
    public String memberJoinForm() {
        return "member/joinForm";
    }

    @PostMapping("/save")
    public String memberSave() {
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
