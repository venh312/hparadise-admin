package com.hparadise.admin.dto.member;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemberPwdRequest {
    private Long id;
    private String pwd;
    private String prevPwd;
    private String tempPwdYn;
    private LocalDateTime modifiedPwdDate;
}
