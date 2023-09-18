package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/messages/*")
public class MessagesServlet extends HttpServlet {
    private final TemplateEngine templateEngine;

    public MessagesServlet(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO : Сделать
        templateEngine.render("chat.ftl", response);
    }
}
