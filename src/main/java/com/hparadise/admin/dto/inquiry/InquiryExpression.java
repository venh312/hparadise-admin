package com.hparadise.admin.dto.inquiry;

import com.hparadise.admin.domain.inquiry.QInquiry;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;

public class InquiryExpression {
    public static BooleanExpression containsTargetDate(QInquiry inquiry, String targetDate, String sDate, String eDate) {
        if (!StringUtils.hasText(targetDate)) return null;
        if (!sDate.isEmpty() && !eDate.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.parse(sDate + "T00:00:00");
            LocalDateTime endDate = LocalDateTime.parse(eDate + "T23:59:59");
            if (targetDate.equals("createDate")) return inquiry.createdDate.gt(startDate).and(inquiry.createdDate.lt(endDate));
            if (targetDate.equals("answerDate")) return inquiry.answerDate.gt(startDate).and(inquiry.answerDate.lt(endDate));
        }
        return null;
    }

    public static BooleanExpression containsTarget(QInquiry inquiry, String target, String search) {
        if (!StringUtils.hasText(target)) return null;
        if (target.equals("name")) return inquiry.name.containsIgnoreCase(search);
        if (target.equals("email")) return inquiry.email.containsIgnoreCase(search);
        return inquiry.name.containsIgnoreCase(search).or(inquiry.email.containsIgnoreCase(search));
    }

    public static BooleanExpression eqAnswerStatus(QInquiry inquiry, String answerStatus) {
        if (!StringUtils.hasText(answerStatus)) return null;
        return inquiry.answerStatus.eq(answerStatus);
    }
}
