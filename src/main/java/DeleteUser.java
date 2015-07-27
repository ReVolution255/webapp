package main.java;

import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("delete"));
        UsersMapper usersMapper;
        SqlSession session = main.java.SessionManager.getSession();
        usersMapper = session.getMapper(UsersMapper.class);
        usersMapper.deleteByPrimaryKey(userId);
        session.commit();
        session.close();
        resp.sendRedirect("/appmain/");
    }
}
