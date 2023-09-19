import controller.*;
import dao.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.*;

/**
 * Hello world!
 *
 */
public class JettyRun
{
    public static void main( String[] args ) throws Exception {
        Dotenv dotenv = Dotenv.load();
        Server server = new Server(8081);

        UserDao userDao = new JdbcUserDao();
        UserService userService = new JdbcUserService(userDao);

        MessageDao messageDao = new JdbcMessageDao();
        MessageService messageService = new JdbcMessageService(messageDao);

        ReactionDao reactionDao = new JdbcReactionDao();
        ReactionService reactionService = new JdbcReactionService(reactionDao);


        System.out.println(userDao.findById(1));

        TemplateEngine templateEngine = new TemplateEngine();

        ServletContextHandler handler = new ServletContextHandler();

        IndexServlet indexServlet = new IndexServlet(templateEngine);
        UsersServlet usersServlet = new UsersServlet(templateEngine);

        LikedServlet likedServlet = new LikedServlet(templateEngine);
        LoginServlet loginServlet = new LoginServlet(templateEngine);
        MessagesServlet messagesServlet = new MessagesServlet(templateEngine);

        handler.addServlet(new ServletHolder(indexServlet), "/");
//        handler.addServlet(new ServletHolder(helloServlet), "/hello");
        handler.addServlet(new ServletHolder(usersServlet), "/users");
        handler.addServlet(CSSBootstrapServlet.class, "/css/bootstrap.min.css");
        handler.addServlet(CSSGlobalServlet.class, "/css/style.css");
//        handler.addServlet(new ServletHolder(countriesServlet), "/countries");
        handler.addServlet(new ServletHolder(likedServlet), "/liked" );
        handler.addServlet(new ServletHolder(loginServlet),"/login");
        handler.addServlet(new ServletHolder(messagesServlet),"/messages/*");
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
