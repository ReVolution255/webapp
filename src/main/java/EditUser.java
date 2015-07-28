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
        //Get data
        Users editedUser = new Users();
        long newId = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name");

        //Handle data
        editedUser.setId(newId);
        editedUser.setName(newName);
        SqlSession session = main.java.SessionManager.getSession();
        UsersMapper usersMapper = session.getMapper(UsersMapper.class);
        usersMapper.updateByPrimaryKey(editedUser);

        //Close session
        session.commit();
        session.close();

        //Send data
        resp.sendRedirect("/appmain/");
    }
}
