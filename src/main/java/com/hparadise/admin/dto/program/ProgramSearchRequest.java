package com.hparadise.admin.dto.program;

import com.hparadise.admin.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ProgramSearchRequest extends BaseTimeEntity {
    private Long id;
    private String title;
    private String contents;
    private String place;
    private String status;
    private String useYn;
    private Long teacherId;
    private Long createdId;
    private Long modifiedId;
    private String targetDate;
    private String startDate;
    private String endDate;
    private String target;
    private String search;
}
