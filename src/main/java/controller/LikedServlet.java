package controller;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/liked")
public class LikedServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;
    private final Long MOCKED_LOGGED_IN_USER_ID = Long.valueOf(1);

    public LikedServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, Object> dataModel = new HashMap<>();

        dataModel.put("userList", userService.findLikedUsersByUserId(MOCKED_LOGGED_IN_USER_ID));

        templateEngine.render("people-list.ftl", dataModel, response);
    }
}