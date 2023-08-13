package com.hparadise.admin.dto.inquiry;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class InquiryAnswerRequest {
    private Long id;
    private String answerStatus;
    private String answerTitle;
    private String answerContents;
}
