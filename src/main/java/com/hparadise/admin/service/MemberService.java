package com.hparadise.admin.service;

import com.hparadise.admin.domain.member.Member;
import com.hparadise.admin.domain.member.MemberRepository;
import com.hparadise.admin.dto.member.MemberInfoResponse;
import com.hparadise.admin.dto.member.MemberSaveRequest;
import com.hparadise.admin.dto.member.MemberPwdRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public long save(MemberSaveRequest request) {
        return memberRepository.save(request.toEntity()).getId();
    }

    public MemberInfoResponse findByEmail(String email) {
        return new MemberInfoResponse(memberRepository.findByEmail(email));
    }

    public int countByEmail(String email) {
        return memberRepository.countByEmail(email);
    }

}
