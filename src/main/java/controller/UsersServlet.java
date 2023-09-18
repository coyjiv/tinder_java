package controller;

import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
//    private final UserService userService;

    public UsersServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
//        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        System.out.println("lol?");
        templateEngine.render("like-page.ftl", response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
//        Dotenv dotenv = Dotenv.load();
//        String dbLogin = dotenv.get("DB_LOGIN");
//
//        String dbConnectionString = dotenv.get("DB_CONNECTION_URL");
//        String dbPassword = dotenv.get("DB_PASSWORD");
//        try (Connection connection = DriverManager.getConnection(dbConnectionString, dbLogin, dbPassword)) {
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM \"test-table\"");
//
//            while (resultSet.next()) {
//
//                String name = resultSet.getString("name");
//                System.out.println("test name output : " + name);
//            }
//
//        } catch (SQLException e){
//            System.out.println("Something went wrong, with db interaction returning countries");
//            e.printStackTrace();
//        }
        templateEngine.render("like-page.ftl", response);
    }

}
