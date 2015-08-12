package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRolesManager implements main.java.IDBUserRolesReader {
    private static final Logger logger = LogManager.getLogger();

    public UserRolesMapper getUserRolesMapper() {
        return userRolesMapper;
    }
    @Autowired
    private UserRolesMapper userRolesMapper;

    public void setUserRolesMapper(UserRolesMapper userRolesMapper) {
        this.userRolesMapper = userRolesMapper;
    }

    public static main.java.IDBUserRolesReader daoFactory(){
        main.java.IDBUserRolesReader reader = new UserRolesManager();
        return reader;
    }

    public List<UserRoles> getUserRoles(UserRolesExample example){
        logger.entry(example);
        List<UserRoles> userRoles = userRolesMapper.selectByExample(example);
        logger.exit(userRoles);
        return userRoles;
    }

    public void insert(UserRoles userRole){
        logger.entry(userRole);
        userRolesMapper.insert(userRole);
    }
    public void updateByPrimaryKey(UserRoles userRole){
        logger.entry(userRole);
        userRolesMapper.updateByPrimaryKey(userRole);
    }
    public UserRoles selectByPrimaryKey(Long id){
        logger.entry(id);
        UserRoles userRole =  userRolesMapper.selectByPrimaryKey(id);
        logger.exit(userRole);
        return userRole;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        userRolesMapper.deleteByPrimaryKey(id);
    }
}
