package me.henu.stdspringmvcmid;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class VisitTimeInterceptor implements HandlerInterceptor {

    /**
     * 방문 시간을 저장하는 인터셉터 preHandle 메소드 구현
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // Session 데이터에 "visitTime" 이름으로 데이터가 없다면 설정
        if (session.getAttribute("visitTime") == null) {
            session.setAttribute("visitTime", LocalDateTime.now());
        }

        return true;
    }
}
