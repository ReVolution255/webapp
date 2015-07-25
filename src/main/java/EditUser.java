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


public class EditUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users editedUser = new Users();
        long newId = Long.parseLong(req.getParameter("id"));
        String newName = req.getParameter("name");
        editedUser.setId(newId);
        editedUser.setName(newName);
        SqlSessionFactory sqlSessionFactory;
        SqlSession session = null;
        UsersMapper usersMapper;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();
            usersMapper = session.getMapper(UsersMapper.class);
            usersMapper.updateByPrimaryKey(editedUser);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            session.commit();
            session.close();
        }
        resp.sendRedirect("/appmain/appmain");
    }
}
