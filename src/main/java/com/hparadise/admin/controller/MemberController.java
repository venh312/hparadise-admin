package com.hparadise.admin.controller;

import com.hparadise.admin.domain.member.Member;
import com.hparadise.admin.dto.member.MemberSaveRequest;
import com.hparadise.admin.dto.member.MemberSearchRequest;
import com.hparadise.admin.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
@Controller
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/public/signin")
    public String loginForm() {
        return "member/signin";
    }

    @GetMapping("/public/signup")
    public String signupForm() {
        return "member/signup";
    }

    @GetMapping("/public/forgot-password")
    public String forgotPasswordForm() {
        return "member/forgot-password";
    }

    @PostMapping("/public/save")
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

    @PostMapping("/public/forgot")
    public String memberForgot(Model model, @RequestBody MemberSaveRequest request) {
        int result = 0;
        try {
            result = memberService.countByEmail(request.getEmail());
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }


    @PostMapping("/public/count-email")
    public String countByEmail(Model model, @RequestBody MemberSaveRequest request) {
        int result = 0;
        try {
            result = memberService.countByEmail(request.getEmail());
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }

    @GetMapping("/list")
    public String memberListPage(Model model, MemberSearchRequest request,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", memberService.findAll(request, page, pageSize));
        return "member/list";
    }

    @GetMapping("/list-search")
    public String memberListSearch(Model model, MemberSearchRequest request,
                                    @RequestParam(required = false, defaultValue = "0") Integer page,
                                    @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", memberService.findAll(request, page, pageSize));
        return "jsonView";
    }

    @GetMapping("/detail")
    public String memberDetailPage(Model model, MemberSearchRequest request) {
        model.addAttribute("info", memberService.findById(request.getId()));
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

    @PostMapping("/delete")
    public String memberDelete() {
        return "jsonView";
    }

    @PutMapping("/role")
    public String updateRole(Model model, @RequestBody MemberSearchRequest request) {
        try {
            model.addAttribute("result", memberService.updateRole(request));
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        return "jsonView";
    }
}
