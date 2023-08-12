package com.hparadise.admin.dto.inquiry;

import com.hparadise.admin.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class InquirySearchRequest extends BaseTimeEntity {
    private Long id;
    private String type;
    private String name;
    private String email;
    private String contents;
    private String phoneNumber;
    private String answerStatus;
    private String answerTitle;
    private String answerContents;
    private LocalDateTime answerDate;
    private String targetDate;
    private String startDate;
    private String endDate;
    private String target;
    private String search;
}
