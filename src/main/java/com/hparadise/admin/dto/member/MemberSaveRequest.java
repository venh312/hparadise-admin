package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.Member;
import lombok.Getter;
import org.jasypt.encryption.ByteEncryptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
public class MemberSaveRequest {
    private String email;
    private String name;
    private String birth;
    private String password;

    public Member toEntity() {
        return Member.builder()
            .email(getEmail())
            .name(getName())
            .birth(getBirth())
            .password(new BCryptPasswordEncoder().encode(getPassword()))
            .tempPasswordYn("N")
            .useYn("Y")
            .build();
    }

}
