package controller;

import domain.User;
import service.UserService;
import utils.CookieUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/liked")
public class LikedServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public LikedServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> dataModel = new HashMap<>();

        long currentUserId = -1;
        if (CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).isPresent()){
            currentUserId = Long.parseLong(CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).get().getValue());
        }

        List<User> listOfLiked = userService.findLikedUsersByUserId(currentUserId);
        if(listOfLiked.isEmpty()){
            try{
                response.sendRedirect("/users");
            } catch (IOException e){
                System.out.println("Error redirecting to  /users ");

            }
        }

        dataModel.put("userList", listOfLiked);

        templateEngine.render("people-list.ftl", dataModel, response);
    }
}