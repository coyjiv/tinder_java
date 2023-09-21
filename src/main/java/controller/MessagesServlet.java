package controller;

import domain.Message;
import domain.User;
import service.MessageService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@WebServlet("/messages/*")
public class MessagesServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final MessageService messageService;
    private final UserService userService;
    private final Long MOCKED_LOGGED_IN_USER_ID = Long.valueOf(3);

    public MessagesServlet(TemplateEngine templateEngine, MessageService messageService, UserService userService) {
        this.templateEngine = templateEngine;
        this.messageService = messageService;
        this.userService = userService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            try{
            if (pathParts.length > 1) {
                String recipientId = pathParts[1];
                int intRecipientId = Integer.parseInt(recipientId);

                String messageContent = request.getParameter("messageContent");
                boolean isMessageSent = messageService.sendMessage(messageContent, Math.toIntExact(MOCKED_LOGGED_IN_USER_ID), intRecipientId);
                if(isMessageSent){
                    try{
                        response.sendRedirect("/messages/"+recipientId);
                    } catch (IOException e){
                        System.out.println("Error redirecting to /messages/*");
                    }
                }
            }} catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String pathInfo = request.getPathInfo();

        if (pathInfo != null && !pathInfo.contains("img")) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                String id = pathParts[1];

                try{

                    List<Message> messages = messageService.findAllBetweenUsers(Integer.parseInt(id), Math.toIntExact(MOCKED_LOGGED_IN_USER_ID));

                    Optional<User> recipient = userService.findById(Integer.parseInt(id));

                    System.out.println(recipient.get().getUsername());


                    if(recipient.isPresent()){
                        Map<String, Object> params = Map.of(
                                "messages",messages,
                                "recipient", recipient.get()
                        );
                        templateEngine.render("chat.ftl", params, response);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("User is non existent");
                    e.printStackTrace();
                }



            }
        }
    }
}
