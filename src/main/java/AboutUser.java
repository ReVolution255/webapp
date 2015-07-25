package main.java;
import main.java.Users;
import main.java.UsersMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;


public class AboutUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Users user = null;
        SqlSessionFactory sqlSessionFactory;
        UsersMapper usersMapper;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            usersMapper = sqlSessionFactory.openSession().getMapper(UsersMapper.class);
            user = usersMapper.selectByPrimaryKey(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        req.setAttribute("user", user);
        req.getRequestDispatcher("/about-user.jsp").forward(req, resp);
    }
}
