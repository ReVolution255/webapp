package main.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AppMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<table border=1>");
        pw.println("</table>");
    }
}