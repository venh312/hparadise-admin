package com.hparadise.admin.handler;

import com.hparadise.admin.common.AuthStatus;
import com.hparadise.admin.domain.member.Member;
import com.hparadise.admin.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("===> onAuthenticationSuccess");
        Member member = (Member) authentication.getPrincipal();

        if (member.getLoginFailCnt() > 9) {
            response.setStatus(AuthStatus.NOT_LOCK.getValue());
        } else if (member.isEnabled() && member.isAccountNonLocked()) {
            RequestContextHolder.getRequestAttributes().setAttribute("member", member, RequestAttributes.SCOPE_SESSION);
            memberService.updateLastLogin(member.getEmail());
            response.sendRedirect("/member/list");
        }
    }
}