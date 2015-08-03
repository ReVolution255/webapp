package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutUser extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.entry(req, resp);
        //Get data
        long id = Long.parseLong(req.getParameter("id"));
        main.java.UsersManager manager = new main.java.UsersManager();
        Users user = manager.selectByPrimaryKey(id);
        //Send data
        req.setAttribute("user", user);
        req.getRequestDispatcher("/about-user.jsp").forward(req, resp);
        logger.exit();
    }
}
