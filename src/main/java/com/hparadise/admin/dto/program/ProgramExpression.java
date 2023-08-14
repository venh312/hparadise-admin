package com.hparadise.admin.dto.program;

import com.hparadise.admin.domain.program.QProgram;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;
import java.time.LocalDateTime;

public class ProgramExpression {
    public static BooleanExpression containsTargetDate(QProgram program, String targetDate, String sDate, String eDate) {
        if (!StringUtils.hasText(targetDate)) return null;
        if (!sDate.isEmpty() && !eDate.isEmpty()) {
            LocalDateTime startDate = LocalDateTime.parse(sDate + "T00:00:00");
            LocalDateTime endDate = LocalDateTime.parse(eDate + "T23:59:59");
            if (targetDate.equals("createDate")) return program.createdDate.gt(startDate).and(program.createdDate.lt(endDate));
        }
        return null;
    }

    public static BooleanExpression containsTarget(QProgram program, String target, String search) {
        if (!StringUtils.hasText(target)) return null;
        if (target.equals("title")) return program.title.containsIgnoreCase(search);
        if (target.equals("contents")) return program.contents.containsIgnoreCase(search);
        if (target.equals("place")) return program.place.containsIgnoreCase(search);
        return program.title.containsIgnoreCase(search)
                .or(program.contents.containsIgnoreCase(search))
                .or(program.place.containsIgnoreCase(search));
    }

    public static BooleanExpression eqStatus(QProgram program, String status) {
        if (!StringUtils.hasText(status)) return null;
        return program.status.eq(status);
    }

    public static BooleanExpression eqUseYn(QProgram program, String useYn) {
        if (!StringUtils.hasText(useYn)) return null;
        return program.useYn.eq(useYn);
    }
}
