package main.java;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.Reader;
import java.util.List;

public class SessionManager {
    public UsersMapper getMapper(SqlSession s){
        return s.getMapper(UsersMapper.class);
    }

    public List<Users> getUsers(UsersExample example){
        SqlSession s = getSession();
        List<Users> users = getMapper(s).selectByExample(example);
        s.commit();
        s.close();
        return users;
    }

    public static SqlSession getSession(){
        SqlSessionFactory sqlSessionFactory;
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            return sqlSessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Users user){
        SqlSession s = getSession();
        getMapper(s).insert(user);
        s.commit();
        s.close();
    }
    public void updateByPrimaryKey(Users user){
        SqlSession s = getSession();
        getMapper(s).updateByPrimaryKey(user);
        s.commit();
        s.close();
    }
    public Users selectByPrimaryKey(Long id){
        SqlSession s = getSession();
        Users user =  getMapper(s).selectByPrimaryKey(id);
        s.commit();
        s.close();
        return user;
    }
    public void deleteByPrimaryKey(Long id){
        SqlSession s = getSession();
        getMapper(s).deleteByPrimaryKey(id);
        s.commit();
        s.close();
    }
}
