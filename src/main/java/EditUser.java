package main.java;

import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users editedUser = new Users();
        long newId = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name");
        editedUser.setId(newId);
        editedUser.setName(newName);
        UsersMapper usersMapper;
        SqlSession session = main.java.SessionManager.getSession();
        usersMapper = session.getMapper(UsersMapper.class);
        usersMapper.updateByPrimaryKey(editedUser);
        session.commit();
        session.close();
        resp.sendRedirect("/appmain/");
    }
}
