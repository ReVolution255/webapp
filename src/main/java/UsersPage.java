package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class UsersPage extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.entry(req, resp);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
        logger.exit();
    }
}