package com.hparadise.admin.dto.program;

import com.hparadise.admin.domain.program.Program;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProgramSaveRequest {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String contents;
    private String place;
    private String status;
    private String useYn;
    private Long teacherId;
    private Long createdId;

    public Program toEntity() {
        return Program.builder()
            .startDate(getStartDate())
            .endDate(getEndDate())
            .title(getTitle())
            .contents(getContents())
            .place(getPlace())
            .status(getStatus())
            .useYn(getUseYn())
            .teacherId(getTeacherId())
            .createdId(getCreatedId())
            .build();
    }

}
