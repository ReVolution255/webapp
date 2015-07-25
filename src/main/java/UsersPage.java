package main.java;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class UsersPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> users = null;
        SqlSessionFactory sqlSessionFactory;
        SqlSession session = null;
        UsersMapper usersMapper;
        Reader reader;
            try {
                reader = Resources.getResourceAsReader("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
                session = sqlSessionFactory.openSession();
                usersMapper = session.getMapper(UsersMapper.class);
                users = usersMapper.selectByExample(null);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            session.close();
        }
        req.setAttribute("usersList", users);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}