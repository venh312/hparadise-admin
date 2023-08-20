package com.hparadise.admin.util;

import com.hparadise.admin.domain.member.Member;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class MemberUtils {
    private static Member getMember() {
        return (Member) RequestContextHolder.getRequestAttributes().getAttribute("member", RequestAttributes.SCOPE_SESSION);
    }
    public static long getId() {
        return getMember().getId();
    }
}
