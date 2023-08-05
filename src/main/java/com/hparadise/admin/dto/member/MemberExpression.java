package com.hparadise.admin.dto.member;

import com.hparadise.admin.domain.member.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberExpression {
    public static BooleanExpression containsTargetDate(QMember member, String targetDate, String sDate, String eDate) {
        if (!StringUtils.hasText(targetDate)) return null;
        LocalDateTime startDate = LocalDateTime.parse(sDate + "T00:00:00");
        LocalDateTime endDate = LocalDateTime.parse(eDate + "T23:59:59");
        if (targetDate.equals("loginDate")) return member.loginDate.gt(startDate).and(member.loginDate.lt(endDate));
        if (targetDate.equals("createDate")) return member.createdDate.gt(startDate).and(member.createdDate.lt(endDate));
        return null;
    }

    public static BooleanExpression containsTarget(QMember member, String target, String search) {
        if (!StringUtils.hasText(target)) return null;
        if (target.equals("name")) return member.name.containsIgnoreCase(search);
        if (target.equals("email")) return member.email.containsIgnoreCase(search);
        return member.name.containsIgnoreCase(search).or(member.email.containsIgnoreCase(search));
    }

    public static BooleanExpression eqUseYn(QMember member, String useYn) {
        if (!StringUtils.hasText(useYn)) return null;
        return member.useYn.eq(useYn);
    }


}
