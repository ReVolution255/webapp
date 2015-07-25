package main.java;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
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
        SqlSession session = null;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();
            usersMapper = session.getMapper(UsersMapper.class);
            int temp = usersMapper.deleteByPrimaryKey(userId);
        } catch (Exception e) {
            System.out.println(e.getCause() + e.getMessage());
        } finally {
            session.commit();
            session.close();
        }
        resp.sendRedirect("/appmain/appmain");
    }
}
