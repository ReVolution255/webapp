package main.java;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AppMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> users = null;
        SqlSessionFactory sqlSessionFactory;
        UsersMapper usersMapper;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            usersMapper = sqlSessionFactory.openSession().getMapper(main.java.UsersMapper.class);
            users = usersMapper.selectByExample(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>Web Application</title>");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<table border=1>");
        try {
            if (users != null) {
                for (Users user : users) {
                    pw.println("<tr>");
                    pw.println("<td>" + user.getId() + "</td>");
                    pw.println("<td>" + user.getName() + "</td>");
                    pw.println("</tr>");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        pw.println("</table>");
        pw.println("</body>");
        pw.println("</html>");
    }
}