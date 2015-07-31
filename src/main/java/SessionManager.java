package main.java;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;

public class SessionManager {
    static SqlSession session = null;
    public static SqlSession getSession(){
        SqlSessionFactory sqlSessionFactory;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            session = sqlSessionFactory.openSession();
        } catch (Exception e) {
            System.out.println(e.getCause() + e.getMessage());
        }
        return session;
    }
    public static void closeSession(){
        session.commit();
        session.close();
    }
}
