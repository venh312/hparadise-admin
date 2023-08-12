package com.hparadise.admin.dto.inquiry;

import com.hparadise.admin.domain.inquiry.Inquiry;
import com.hparadise.admin.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class InquiryInfoResponse {
    private Long id;
    private String type;
    private String name;
    private String email;
    private String contents;
    private String phoneNumber;
    private String answerStatus;
    private String answerTitle;
    private String answerContents;
    private String answerDate;
    private String createdDate;
    private String modifiedDate;

    public InquiryInfoResponse(Inquiry inquiry) {
        this.id = inquiry.getId();
        this.type = inquiry.getType();
        this.name = inquiry.getName();
        this.email = inquiry.getEmail();
        this.contents = inquiry.getContents();
        this.phoneNumber = inquiry.getPhoneNumber();
        this.answerStatus = inquiry.getAnswerStatus();
        this.answerTitle = inquiry.getAnswerTitle();
        this.answerContents = inquiry.getAnswerContents();
        this.answerDate = DateUtils.toStringDateTime(inquiry.getAnswerDate());
        this.createdDate = DateUtils.toStringDateTime(inquiry.getCreatedDate());
        this.modifiedDate = DateUtils.toStringDateTime(inquiry.getModifiedDate());
    }

    public InquiryInfoResponse(Long id, String name, String email, String answerStatus, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.answerStatus = answerStatus;
        this.createdDate = DateUtils.toStringDateTime(createdDate);
    }
}
