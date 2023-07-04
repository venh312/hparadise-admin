package com.hparadise.admin.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue
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
}
