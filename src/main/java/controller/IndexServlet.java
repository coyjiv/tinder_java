package controller;


import io.github.cdimascio.dotenv.Dotenv;
import utils.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public IndexServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        Map<String, Object> params = Map.of(
//                "user", request.getSession().getAttribute(LoginFilter.ACTIVE_USER_ATTR_NAME)
//        );
//        templateEngine.render("dashboard.ftl", params, response);
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Dotenv dotenv = Dotenv.load();
        String dbLogin = dotenv.get("DB_LOGIN");

        String dbConnectionString = dotenv.get("DB_CONNECTION_URL");
        String dbPassword = dotenv.get("DB_PASSWORD");

        long sessionUserId = -1;
        if (CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).isPresent()){
            sessionUserId = Long.parseLong(CookieUtil.findCookieByName(request, LoginFilter.SESSION_USER_ID).get().getValue());
        }
        try (Connection connection = DriverManager.getConnection(dbConnectionString, dbLogin, dbPassword)) {
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM users WHERE user_id = ?");
            statement.setLong(1,sessionUserId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                String name = resultSet.getString("username");
                Map<String, Object> params = Map.of(
                        "userName", name
                );
                templateEngine.render("dashboard.ftl", params,response);
            }

        } catch (SQLException e){
            System.out.println("Something went wrong, with db interaction returning countries");
            e.printStackTrace();
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Received a request");
    }
}
