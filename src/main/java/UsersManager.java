package main.java;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UsersManager implements main.java.IDBReader {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private UsersMapper usersMapper;

    public void setUsersMapper(UsersMapper userMapper) {
        this.usersMapper = userMapper;
    }

    public static main.java.IDBReader daoFactory(){
        main.java.IDBReader reader = new UsersManager();
        return reader;
    }

    public List<Users> getUsers(UsersExample example){
        logger.entry(example);
        List<Users> users = usersMapper.selectByExample(example);
        logger.exit(users);
        return users;
    }

    public void insert(Users user){
        logger.entry(user);
        usersMapper.insert(user);
    }
    public void updateByPrimaryKey(Users user){
        logger.entry(user);
        usersMapper.updateByPrimaryKey(user);
    }
    public Users selectByPrimaryKey(Long id){
        logger.entry(id);
        Users user =  usersMapper.selectByPrimaryKey(id);
        logger.exit(user);
        return user;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        usersMapper.deleteByPrimaryKey(id);
    }
}
