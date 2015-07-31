package main.java;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get data
        long id = Long.parseLong(req.getParameter("id"));
        main.java.UsersManager manager = new main.java.UsersManager();
        Users user = manager.selectByPrimaryKey(id);
        //Send data
        req.setAttribute("user", user);
        req.getRequestDispatcher("/about-user.jsp").forward(req, resp);
    }
}
