package com.hparadise.admin.dto.program;

import com.hparadise.admin.domain.program.Program;
import com.hparadise.admin.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProgramInfoResponse {
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
    private Long modifiedId;
    private String createdDate;
    private String modifiedDate;

    public ProgramInfoResponse(Program program) {
        this.id = program.getId();
        this.startDate = program.getStartDate();
        this.endDate = program.getEndDate();
        this.title = program.getTitle();
        this.contents = program.getContents();
        this.place = program.getPlace();
        this.status = program.getStatus();
        this.useYn = program.getUseYn();
        this.teacherId = program.getTeacherId();
        this.createdId = program.getCreatedId();
        this.modifiedId = program.getModifiedId();
        this.createdDate = DateUtils.toStringDateTime(program.getCreatedDate());
        this.modifiedDate = DateUtils.toStringDateTime(program.getModifiedDate());
    }

    public ProgramInfoResponse(Long id, String title, String place, String status, String useYn, LocalDateTime createdDate) {
        this.id = id;
        this.title = title;
        this.place = place;
        this.status = status;
        this.useYn = useYn;
        this.createdDate = DateUtils.toStringDateTime(createdDate);
    }
}
