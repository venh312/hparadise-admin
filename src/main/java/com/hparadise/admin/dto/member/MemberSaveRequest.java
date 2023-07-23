package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.Member;
import lombok.Getter;
import org.jasypt.encryption.ByteEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class MemberSaveRequest {
    private String name;
    private String email;
    private String password;

    public Member toEntity() {
        return Member.builder()
            .name(getName())
            .email(getEmail())
            .password(new BCryptPasswordEncoder().encode(getPassword()))
            .tempPasswordYn("N")
            .useYn("Y")
            .build();
    }

}
