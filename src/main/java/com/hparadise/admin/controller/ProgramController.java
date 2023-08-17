package com.hparadise.admin.controller;

import com.hparadise.admin.dto.program.ProgramSaveRequest;
import com.hparadise.admin.dto.program.ProgramSearchRequest;
import com.hparadise.admin.service.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/program")
@Controller
public class ProgramController {
    private final ProgramService programService;

    @GetMapping("/list")
    public String programListPage(Model model, ProgramSearchRequest request,
          @RequestParam(required = false, defaultValue = "0") Integer page,
          @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", programService.findAll(request, page, pageSize));
        return "/program/list";
    }

    @GetMapping("/list-search")
    public String programListSearch(Model model, ProgramSearchRequest request,
           @RequestParam(required = false, defaultValue = "0") Integer page,
           @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
        model.addAttribute("resultMap", programService.findAll(request, page, pageSize));
        return "jsonView";
    }

    @GetMapping("/detail")
    public String programDetailPage(Model model, ProgramSearchRequest request) {
        if (request.getId() != null) {
            model.addAttribute("info", programService.findById(request.getId()));
        }
        return "program/detail";
    }

    @PostMapping("/save")
    public String programSave(Model model, @RequestBody ProgramSaveRequest request) {
        long result = 0;
        try {
            result = programService.save(request);
        } catch (Exception e) {
            log.info("[Exception] : {} ", e.getMessage());
        }
        model.addAttribute("result", result);
        return "jsonView";
    }
}
