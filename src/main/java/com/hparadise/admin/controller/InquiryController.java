package com.hparadise.admin.controller;

import com.hparadise.admin.dto.inquiry.InquirySearchRequest;
import com.hparadise.admin.dto.member.MemberSearchRequest;
import com.hparadise.admin.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/inquiry")
@Controller
public class InquiryController {
    private final InquiryService inquiryService;

    @GetMapping("/list")
    public String inquiryListPage(Model model, InquirySearchRequest request,
                                  @RequestParam(required = false, defaultValue = "0") Integer page,
                                  @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", inquiryService.findAll(request, page, pageSize));
        return "/inquiry/list";
    }

    @GetMapping("/list-search")
    public String inquiryListSearch(Model model, InquirySearchRequest request,
                                   @RequestParam(required = false, defaultValue = "0") Integer page,
                                   @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", inquiryService.findAll(request, page, pageSize));
        return "jsonView";
    }

    @GetMapping("/detail")
    public String inquiryDetailPage(Model model, MemberSearchRequest request) {
        model.addAttribute("info", inquiryService.findById(request.getId()));
        return "inquiry/detail";
    }
}
