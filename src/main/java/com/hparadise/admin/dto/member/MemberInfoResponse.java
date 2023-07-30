package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.BaseTimeEntity;
import com.hparadise.admin.domain.member.Member;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemberInfoResponse extends BaseTimeEntity {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String prevPassword;
    private String tempPasswordYn;
    private int loginFailCnt;
    private LocalDateTime modifiedPasswordDate;
    private String useYn;
    private String loginDate;
    private String createDate;

    public MemberInfoResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.prevPassword = member.getPrevPassword();
        this.tempPasswordYn = member.getTempPasswordYn();
        this.loginFailCnt = member.getLoginFailCnt();
        this.modifiedPasswordDate = member.getModifiedPasswordDate();
        this.loginDate = getLoginDate();
        this.useYn = member.getUseYn();
    }

    public MemberInfoResponse(Long id, String name, String email, String useYn, LocalDateTime loginDate, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.useYn = useYn;
        this.loginDate = toStringDateTime(loginDate);
        this.createDate = toStringDateTime(createDate);
    }
}
