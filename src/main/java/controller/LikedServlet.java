package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/liked")
public class LikedServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public LikedServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO : Сделать

    }
}
