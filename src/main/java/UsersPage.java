package main.java;

import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

public class UsersPage extends HttpServlet {
    private ServletContext context;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.context = config.getServletContext();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean dataChanged = false;
        String action = "";
        //AJAX logic
        if (req.getParameter("action") != null)
        action = req.getParameter("action");
        if (action.equals("create")){
            //Get data
            Users newUser = new Users();
            String newName = req.getParameter("name");

            //Handle data
            newUser.setName(newName);
            SqlSession session = main.java.SessionManager.getSession();
            UsersMapper usersMapper = session.getMapper(UsersMapper.class);
            usersMapper.insert(newUser);

            //Close session
            session.commit();
            session.close();
            dataChanged = true;
        } else if (action.equals("delete")){
            //Get data
            long userId = Long.parseLong(req.getParameter("delete"));

            //Handle data
            SqlSession session = main.java.SessionManager.getSession();
            UsersMapper usersMapper = session.getMapper(UsersMapper.class);
            usersMapper.deleteByPrimaryKey(userId);

            //Close session
            session.commit();
            session.close();
            dataChanged = true;
        } else if (action.equals("edit")){
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
            dataChanged = true;
        }

        //Handle data
        SqlSession session = main.java.SessionManager.getSession();
        UsersMapper usersMapper;
        usersMapper = session.getMapper(UsersMapper.class);
        List<Users> users = usersMapper.selectByExample(null);

        //Close session
        session.commit();
        session.close();

        if (dataChanged){
            resp.setHeader("Cache-Control", "no-cache");
            resp.getWriter().write(new Gson().toJson(users));
        } else {
        //Send data
        req.setAttribute("usersList", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);}
    }
}