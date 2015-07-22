import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/appmain")
public class AppMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        List<Users> users = null;
        SqlSessionFactory sqlSessionFactory;
        UsersMapper usersMapper;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            usersMapper = sqlSessionFactory.openSession().getMapper(UsersMapper.class);
            users = usersMapper.selectByExample(new UsersExample());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
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
    }
}