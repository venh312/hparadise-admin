package com.hparadise.admin.service;

import com.hparadise.admin.domain.member.Member;
import com.hparadise.admin.domain.member.MemberRepository;
import com.hparadise.admin.domain.member.QMember;
import com.hparadise.admin.dto.member.MemberInfoResponse;
import com.hparadise.admin.dto.member.MemberSaveRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final JPAQueryFactory jpaQueryFactory;
    private final QMember member = QMember.member;

    public long save(MemberSaveRequest request) throws Exception {
        return memberRepository.save(request.toEntity()).getId();
    }

    public MemberInfoResponse findByEmail(String email) {
        return new MemberInfoResponse(memberRepository.findByEmail(email));
    }

    public int countByEmail(String email) {
        return memberRepository.countByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) throw new UsernameNotFoundException("==> Not Found Member.");
        return member;
    }

    @Transactional
    public long updateLoginFailCnt(String email) {
        return jpaQueryFactory.update(member)
            .set(member.loginFailCnt, member.loginFailCnt.add(1))
            .set(member.loginDate, LocalDateTime.now())
            .where(member.email.eq(email))
            .execute();
    }

    @Transactional
    public long updateLastLogin(String email) {
        return jpaQueryFactory.update(member)
            .set(member.loginFailCnt, 0)
            .set(member.loginDate, LocalDateTime.now())
            .where(member.email.eq(email))
            .execute();
    }
}
