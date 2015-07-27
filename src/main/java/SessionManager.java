package main.java;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionManager {
    private static DataSource ds;
    private static Connection conn;

    public static SqlSession getSession(){
        try {
            Context ctx = new InitialContext();
            ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgre");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory;
        SqlSession session = null;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession(conn);
        } catch (Exception e) {
            System.out.println(e.getCause() + e.getMessage());
        }
        return session;
    }
}
