package controller;

import utils.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LogoutServlet extends HttpServlet {
    TemplateEngine templateEngine;

    public LogoutServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Cookie> sessionId = CookieUtil.findCookieByName(req, LoginFilter.SESSION_USER_ID);
        req.getSession(false).invalidate();
        sessionId.ifPresent(session -> {
            session.setMaxAge(0);
            session.setPath("/");
            resp.addCookie(session);
        });
        templateEngine.render("login.ftl", resp);
    }
}
