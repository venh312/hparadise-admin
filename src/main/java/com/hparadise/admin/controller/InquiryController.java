package com.hparadise.admin.controller;

import com.hparadise.admin.dto.inquiry.InquiryAnswerRequest;
import com.hparadise.admin.dto.inquiry.InquirySearchRequest;
import com.hparadise.admin.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String inquiryDetailPage(Model model, InquirySearchRequest request) {
        model.addAttribute("info", inquiryService.findById(request.getId()));
        return "inquiry/detail";
    }

    @PostMapping("/answer")
    public String inquiryAnswerSave(Model model, @RequestBody InquiryAnswerRequest request) {
        long result = 0;
        try {
            result = inquiryService.inquiryAnswerSave(request);
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }
}
