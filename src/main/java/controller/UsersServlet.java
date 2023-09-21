package controller;

import domain.User;
import io.github.cdimascio.dotenv.Dotenv;
import service.ReactionService;
import service.UserService;
import utils.CookieUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;
    private final ReactionService reactionService;

    public UsersServlet(TemplateEngine templateEngine, UserService userService, ReactionService reactionService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
        this.reactionService = reactionService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        // Assuming user ID 1 for now; replace this with the actual logged-in user's ID
        // change or remove from db for testing

        long currentUserId = -1;
        if (CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).isPresent()){
            currentUserId = Long.parseLong(CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).get().getValue());
        }

        Long targetUserId = Long.parseLong(request.getParameter("targetUserId"));
        String action = request.getParameter("action"); // "like" or "dislike"

        if ("like".equals(action)) {
            reactionService.likeUser(currentUserId, targetUserId);
        } else if ("dislike".equals(action)) {
            reactionService.dislikeUser(currentUserId, targetUserId);
        }

        try{
            response.sendRedirect("/users");
        } catch (IOException e){
            System.out.println("Error redirecting to /users");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        long currentUserId = -1;
        if (CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).isPresent()){
            currentUserId = Long.parseLong(CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).get().getValue());
        }

        Optional<User> nextUser = userService.getNextUserToReact(currentUserId);

        nextUser.ifPresent(user -> {
            Map<String, Object> params = Map.of("user", user);
            templateEngine.render("like-page.ftl", params, response);
        });
        try{
        if (nextUser.isEmpty()) {
            response.sendRedirect("/liked");
        }
        } catch (IOException e){
            System.out.println("Error redirecting to /liked");
            e.printStackTrace();
        }
    }

}
