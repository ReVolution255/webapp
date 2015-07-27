package main.java;

import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users newUser = new Users();
        //long newId = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name");
        //newUser.setId(newId);
        newUser.setName(newName);
        UsersMapper usersMapper;
        SqlSession session = main.java.SessionManager.getSession();
        usersMapper = session.getMapper(UsersMapper.class);
        usersMapper.insert(newUser);
        session.commit();
        session.close();
        resp.sendRedirect("/appmain/");
    }
}
