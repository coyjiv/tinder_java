package controller;

import domain.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LoginFilter implements Filter {
    public static final String SESSION_USER_ID = "SESSION_USER_ID";
    TemplateEngine templateEngine;
    UserService userService;

    public LoginFilter(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        boolean isAsset = req.getServletPath().equals("/css/bootstrap.min.css") || req.getServletPath().equals("/css/style.css") || req.getServletPath().equals("/js/bootstrap.min.js" ) || req.getServletPath().equals("/registration");
        if (session != null || isAsset) {
            chain.doFilter(req, resp);
            return;
        }
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = userService.findByUsername(login);
        if (user.isPresent() && Objects.equals(user.get().getPassword(), password)) {
            session = req.getSession(true);
            session.setMaxInactiveInterval(60_000);
            Cookie cookie = new Cookie(SESSION_USER_ID, user.get().getUserId().toString());
            resp.addCookie(cookie);
            chain.doFilter(req, resp);
        } else {
            templateEngine.render("login.ftl",resp);
        }
    }

    @Override
    public void destroy() {

    }
}
