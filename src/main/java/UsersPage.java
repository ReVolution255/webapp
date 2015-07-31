package main.java;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class UsersPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        main.java.UsersManager manager = new main.java.UsersManager();
        List<Users> users = manager.getUsers(null);
        //Send data
        req.setAttribute("usersList", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);}
}