package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.Member;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemberInfoResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String prevPassword;
    private String tempPasswordYn;
    private int loginFailCnt;
    private LocalDateTime modifiedPasswordDate;
    private LocalDateTime loginDate;
    private String useYn;

    public MemberInfoResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.prevPassword = member.getPrevPassword();
        this.tempPasswordYn = member.getTempPasswordYn();
        this.loginFailCnt = member.getLoginFailCnt();
        this.modifiedPasswordDate = member.getModifiedPasswordDate();
        this.loginDate = member.getLoginDate();
        this.useYn = member.getUseYn();
    }
}
