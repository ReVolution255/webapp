package main.java;

import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AboutUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Users user = null;
        UsersMapper usersMapper;
        SqlSession session = main.java.SessionManager.getSession();
        usersMapper = session.getMapper(UsersMapper.class);
        user = usersMapper.selectByPrimaryKey(id);
        session.commit();
        session.close();
        req.setAttribute("user", user);
        req.getRequestDispatcher("/about-user.jsp").forward(req, resp);
    }
}
