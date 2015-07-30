package main.java;

import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

public class UsersPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Handle data
        SqlSession session = main.java.SessionManager.getSession();
        UsersMapper usersMapper;
        usersMapper = session.getMapper(UsersMapper.class);
        List<Users> users = usersMapper.selectByExample(null);

        //Close session
        session.commit();
        session.close();

        //Send data
        req.setAttribute("usersList", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);}
}