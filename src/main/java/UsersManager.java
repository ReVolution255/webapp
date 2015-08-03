package main.java;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class UsersManager {
    private static final Logger logger = LogManager.getLogger();
    private static SqlSessionFactory build(){
        logger.entry();
        SqlSessionFactory factory = null;
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            logger.catching(e);
        }
        logger.exit(factory);
        return factory;
    }

    private static SqlSessionFactory sqlSessionFactoryInstance;

    private static SqlSessionFactory getFactory(){
        logger.entry();
        if (sqlSessionFactoryInstance == null)
            sqlSessionFactoryInstance = build();
        logger.exit(sqlSessionFactoryInstance);
        return sqlSessionFactoryInstance;
    }

    public UsersMapper getMapper(SqlSession s){
        logger.entry(s);
        logger.exit();
        return s.getMapper(UsersMapper.class);
    }

    public List<Users> getUsers(UsersExample example){
        logger.entry(example);
        SqlSession s = getSession();
        List<Users> users = getMapper(s).selectByExample(example);
        s.commit();
        s.close();
        logger.exit(users);
        return users;
    }

    private SqlSession getSession(){
        logger.entry();
        try {
            logger.exit();
            return getFactory().openSession();
        } catch (Exception e) {
            logger.catching(e);
        }
        logger.exit();
        return null;
    }

    public void insert(Users user){
        logger.entry(user);
        SqlSession s = getSession();
        getMapper(s).insert(user);
        if (s != null) {
            s.commit();
        }
        s.close();
        logger.exit();
    }
    public void updateByPrimaryKey(Users user){
        logger.entry(user);
        SqlSession s = getSession();
        getMapper(s).updateByPrimaryKey(user);
        if (s != null) {
            s.commit();
        }
        s.close();
        logger.exit();
    }
    public Users selectByPrimaryKey(Long id){
        logger.entry(id);
        SqlSession s = getSession();
        Users user =  getMapper(s).selectByPrimaryKey(id);
        if (s != null) {
            s.commit();
        }
        s.close();
        logger.exit(user);
        return user;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        SqlSession s = getSession();
        getMapper(s).deleteByPrimaryKey(id);
        if (s != null) {
            s.commit();
        }
        s.close();
        logger.exit();
    }
}
