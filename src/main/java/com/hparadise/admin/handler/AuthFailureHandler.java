package com.hparadise.admin.handler;

import com.hparadise.admin.common.AuthStatus;
import com.hparadise.admin.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.info("===> onAuthenticationFailure");

        if (exception instanceof DisabledException)
            response.setStatus(AuthStatus.NOT_ENABLED.getValue());
        else if (exception instanceof CredentialsExpiredException)
            response.setStatus(AuthStatus.NOT_CREDENTIAL.getValue());
        else if (exception instanceof LockedException)
            response.setStatus(AuthStatus.NOT_LOCK.getValue());
        else
            response.setStatus(AuthStatus.NOT_MATCH.getValue());

        memberService.updateLoginFailCnt(request.getParameter("email"));
    }
}