package controller;


import io.github.cdimascio.dotenv.Dotenv;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Map;
import java.util.Objects;

public class IndexServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public IndexServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
      try{
          response.sendRedirect("/users");
      } catch (IOException exception){
          System.out.println("error in redirecting to /users route");
      }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Received a request");
    }
}
