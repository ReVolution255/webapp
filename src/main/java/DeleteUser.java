package main.java;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;


public class DeleteUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("delete"));
        SqlSessionFactory sqlSessionFactory;
        UsersMapper usersMapper;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            usersMapper = sqlSessionFactory.openSession().getMapper(UsersMapper.class);
            usersMapper.deleteByPrimaryKey(userId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/appmain/appmain");
    }
}