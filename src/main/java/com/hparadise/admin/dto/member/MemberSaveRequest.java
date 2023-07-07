package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberSaveRequest {
    private String name;
    private String email;
    private String pwd;

    public Member toEntity() {
        return Member.builder()
            .name(getName())
            .email(getEmail())
            .pwd(getPwd())
            .build();
    }

}
