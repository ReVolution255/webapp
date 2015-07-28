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
        //Get data
        long id = Long.parseLong(req.getParameter("id"));

        //Handle data
        SqlSession session = main.java.SessionManager.getSession();
        UsersMapper usersMapper = session.getMapper(UsersMapper.class);
        Users user = usersMapper.selectByPrimaryKey(id);

        //Close session
        session.commit();
        session.close();

        //Send data
        req.setAttribute("user", user);
        req.getRequestDispatcher("/about-user.jsp").forward(req, resp);
    }
}
