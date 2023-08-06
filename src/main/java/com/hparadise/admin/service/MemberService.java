package com.hparadise.admin.service;

import com.hparadise.admin.domain.member.Member;
import com.hparadise.admin.domain.member.MemberRepository;
import com.hparadise.admin.domain.member.QMember;
import com.hparadise.admin.dto.member.MemberExpression;
import com.hparadise.admin.dto.member.MemberInfoResponse;
import com.hparadise.admin.dto.member.MemberSaveRequest;
import com.hparadise.admin.dto.member.MemberSearchRequest;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Transactional(readOnly = true)
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

    public HashMap<String, Object> findAll(MemberSearchRequest request, Integer page, Integer pageSize) {
        HashMap<String, Object> resultMap = new HashMap<>();

        List<MemberInfoResponse> list = jpaQueryFactory
            .select(Projections.constructor(MemberInfoResponse.class,
                member.id,
                member.name,
                member.email,
                member.useYn,
                member.loginDate,
                member.createdDate
    //                        ExpressionUtils.as(
    //                            JPAExpressions
    //                                .select(member.name)
    //                                .from(member)
    //                                .where(member.id.eq(board.registerId)), "registerName")
            ))
            .from(member)
            .where(
                MemberExpression.containsTargetDate(member, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                MemberExpression.containsTarget(member, request.getTarget(), request.getSearch()),
                MemberExpression.eqUseYn(member, request.getUseYn())
            )
            .offset(page)
            .limit(pageSize)
            .orderBy(member.createdDate.desc())
            .fetch();

        Long totalCnt = (long) jpaQueryFactory.select(member.count()).from(member)
            .where(
                MemberExpression.containsTargetDate(member, request.getTargetDate(), request.getStartDate(), request.getEndDate()),
                MemberExpression.containsTarget(member, request.getTarget(), request.getSearch()),
                MemberExpression.eqUseYn(member, request.getUseYn())
            )
            .fetchOne();

        int totalPage = (int) Math.ceil((float) totalCnt / pageSize);
        totalPage = totalPage == 0 ? 1 : totalPage;
        int currentPage = (page / pageSize) + 1;
        int endPage = pageSize > totalPage ? totalPage : pageSize;

        resultMap.put("list", list);
        resultMap.put("request", request);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);
        resultMap.put("currentPage", currentPage);
        resultMap.put("startPage", currentPage > 4 ? currentPage - 4 : 1);
        resultMap.put("endPage", currentPage > 4 ? Math.min(currentPage + 4, totalPage) : endPage);
        resultMap.put("totalCnt", totalCnt);
        resultMap.put("totalPage", totalPage);

        return resultMap;
    }

    public MemberInfoResponse findById(Long id) {
        return new MemberInfoResponse(memberRepository.findById(id).get());
    }

    @Transactional
    public long updateRole(MemberSearchRequest request) {
        return jpaQueryFactory.update(member)
            .set(member.role, request.getRole())
            .where(member.id.eq(request.getId()))
            .execute();
    }
}
