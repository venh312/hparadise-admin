package com.hparadise.admin.dto.member;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class MemberPwdRequest {
    private Long id;
    private String password;
    private String prevPassword;
    private String tempPasswordYn;
    private LocalDateTime modifiedPasswordDate;
}
