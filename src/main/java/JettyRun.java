import controller.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Hello world!
 *
 */
public class JettyRun
{
    public static void main( String[] args ) throws Exception {
        Dotenv dotenv = Dotenv.load();
        Server server = new Server(8081);

        TemplateEngine templateEngine = new TemplateEngine();

        ServletContextHandler handler = new ServletContextHandler();

        IndexServlet indexServlet = new IndexServlet(templateEngine);
        LikedServlet likedServlet = new LikedServlet(templateEngine);
        LoginServlet loginServlet = new LoginServlet(templateEngine);
        MessagesServlet messagesServlet = new MessagesServlet(templateEngine);

        handler.addServlet(new ServletHolder(indexServlet), "/");
        handler.addServlet(new ServletHolder(likedServlet), "/liked" );
        handler.addServlet(new ServletHolder(loginServlet),"/login");
        handler.addServlet(new ServletHolder(messagesServlet),"/messages/*");
//        handler.addServlet(new ServletHolder(usersServlet), "/users");
//        handler.addServlet(new ServletHolder(countriesServlet), "/liked");
//        handler.addServlet(new ServletHolder(logoutServlet), "/logout");
//        handler.addFilter(new FilterHolder(new LoginFilter(templateEngine, userService)), "/*", EnumSet.of(DispatcherType.REQUEST));
//        handler.addServlet(CSSBootstrapServlet.class, "/css/bootstrap.min.css");
//        handler.addServlet(JsBootstrapServlet.class, "/js/bootstrap.min.css");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}
