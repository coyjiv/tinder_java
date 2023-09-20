package controller;

import domain.User;
import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/liked")
public class LikedServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public LikedServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Dotenv dotenv = Dotenv.load();
        String dbLogin = dotenv.get("DB_LOGIN");
        String dbConnectionString = dotenv.get("DB_CONNECTION_URL");
        String dbPassword = dotenv.get("DB_PASSWORD");

        Map<String, Object> dataModel = new HashMap<>();
        List<User> usersList = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver");

            try (Connection connection = DriverManager.getConnection(dbConnectionString, dbLogin, dbPassword);
                 Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM public.users")) {

                while (rs.next()) {
                   // String userId = rs.getString("user_id");
                    String username = rs.getString("username");
                    String avatarUrl = rs.getString("profile_photo");

                    usersList.add( new User(avatarUrl, username));

                    //TODO : Доделать с использованием полного пользователя и выводить пролайканых , а не всех!
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dataModel.put("userList", usersList);

        templateEngine.render("people-list.ftl", dataModel, response);
    }
}