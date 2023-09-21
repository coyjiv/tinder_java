package controller;

import service.UserService;
import utils.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserService userService;

    public RegistrationServlet(TemplateEngine templateEngine, UserService userService) {
        this.templateEngine = templateEngine;
        this.userService = userService;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        templateEngine.render("registration.ftl", response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String profilePhoto = "https://images.unsplash.com/photo-1660715745143-0bae32bb2029?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjgxfHxudWxsJTIwdXNlciUyMGljb258ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&w=1000&q=60"; // Замените на URL вашей иконки

        String sql = "INSERT INTO users (username, profile_photo, password) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, profilePhoto);
            preparedStatement.setString(3, password);

            int changedRows = preparedStatement.executeUpdate();

            response.sendRedirect("/users");

        } catch (SQLException e) {
            System.out.println("Something went wrong with creation user in DB");
            e.printStackTrace();
        }

    }
}