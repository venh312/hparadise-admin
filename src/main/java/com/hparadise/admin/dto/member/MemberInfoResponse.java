package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.Member;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemberInfoResponse {
    private Long id;
    private String name;
    private String email;
    private String pwd;
    private String prevPwd;
    private String tempPwdYn;
    private int loginFailCnt;
    private LocalDateTime modifiedPwdDate;
    private LocalDateTime loginDate;
    private String useYn;

    public MemberInfoResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.pwd = member.getPwd();
        this.prevPwd = member.getPrevPwd();
        this.tempPwdYn = member.getTempPwdYn();
        this.loginFailCnt = member.getLoginFailCnt();
        this.modifiedPwdDate = member.getModifiedPwdDate();
        this.loginDate = member.getLoginDate();
        this.useYn = member.getUseYn();
    }
}
