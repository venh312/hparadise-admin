package com.hparadise.admin.handler;

import com.hparadise.admin.service.MemberService;
import jakarta.servlet.ServletException;
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
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MemberService memberService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("==> onAuthenticationFailure");
        String email= request.getParameter("email");
        String msg = "msg.member.not.match";

        if (exception instanceof DisabledException) {
            msg = "msg.member.not.enabled";
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "msg.member.not.credentialsExpired";
        } else if (exception instanceof LockedException) {
            msg = "msg.member.not.accountLocked";
        }

        //memberService.updateLoginFailCount(email);

        setDefaultFailureUrl("/member/public/login?msg=" + msg + "&email=" + email);
        super.onAuthenticationFailure(request, response, exception);
    }
}