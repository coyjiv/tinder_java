import controller.*;
import dao.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

/**
 * Hello world!
 *
 */
public class JettyRun
{
    public static void main( String[] args ) throws Exception {
        Dotenv dotenv = Dotenv.load();
        Server server = new Server(Integer.parseInt(System.getenv("PORT")));

        MessageDao messageDao = new JdbcMessageDao();
        MessageService messageService = new JdbcMessageService(messageDao);

        ReactionDao reactionDao = new JdbcReactionDao();
        ReactionService reactionService = new JdbcReactionService(reactionDao);

        UserDao userDao = new JdbcUserDao();
        UserService userService = new JdbcUserService(userDao, reactionDao);

        System.out.println(userDao.findById(1));

        TemplateEngine templateEngine = new TemplateEngine();

        ServletContextHandler handler = new ServletContextHandler();
        SessionHandler sessionHandler = new SessionHandler();
        handler.setSessionHandler(sessionHandler);
        IndexServlet indexServlet = new IndexServlet(templateEngine);
        UsersServlet usersServlet = new UsersServlet(templateEngine, userService, reactionService);

        LikedServlet likedServlet = new LikedServlet(templateEngine, userService);
        RegistrationServlet registrationServlet = new RegistrationServlet(templateEngine,userService);
        LoginServlet loginServlet = new LoginServlet(templateEngine);
        LogoutServlet logoutServlet = new LogoutServlet(templateEngine);
        Filter loginFilter = new LoginFilter(templateEngine, userService);
        MessagesServlet messagesServlet = new MessagesServlet(templateEngine, messageService, userService);

        handler.addServlet(new ServletHolder(indexServlet), "/");
        handler.addFilter(new FilterHolder(loginFilter), "/*", EnumSet.of(DispatcherType.REQUEST));
         handler.addServlet(new ServletHolder(registrationServlet),"/registration");
//        handler.addServlet(new ServletHolder(helloServlet), "/hello");
        handler.addServlet(new ServletHolder(usersServlet), "/users");
        handler.addServlet(CSSBootstrapServlet.class, "/css/bootstrap.min.css");
        handler.addServlet(CSSGlobalServlet.class, "/css/style.css");
//        handler.addServlet(new ServletHolder(countriesServlet), "/countries");
        handler.addServlet(new ServletHolder(likedServlet), "/liked" );
        handler.addServlet(new ServletHolder(loginServlet),"/login");
        handler.addServlet(new ServletHolder(messagesServlet),"/messages/*");
        handler.addServlet(new ServletHolder(logoutServlet),"/logout");
//        handler.addServlet(new ServletHolder(usersServlet), "/users");
//        handler.addServlet(new ServletHolder(countriesServlet), "/liked");
//        handler.addServlet(new ServletHolder(logoutServlet), "/logout");
//        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, userService)), "/*", EnumSet.of(DispatcherType.REQUEST));
        handler.addServlet(JsBootstrapServlet.class, "/js/bootstrap.min.js");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
