package controller;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsBootstrapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException {
        try (ServletOutputStream os = rs.getOutputStream()) {
            InputStream stream = this.getClass().getClassLoader().getResourceAsStream("js/bootstrap.min.js");
            if (stream != null) {
                byte[] css = new byte[1024];
                int bytesRead;
                while ((bytesRead = stream.read(css)) != -1) {
                    os.write(css, 0, bytesRead);
                }
                stream.close();
            } else {
                rs.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
