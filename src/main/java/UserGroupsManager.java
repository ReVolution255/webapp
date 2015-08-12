package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupsManager implements main.java.IDBUserGroupsReader {
    private static final Logger logger = LogManager.getLogger();

    public UserGroupsMapper getUsersMapper() {
        return userGroupsMapper;
    }
    @Autowired
    private UserGroupsMapper userGroupsMapper;

    public void setUserGroupsMapper(UserGroupsMapper userGroupsMapper) {
        this.userGroupsMapper = userGroupsMapper;
    }

    public static main.java.IDBUserGroupsReader daoFactory(){
        main.java.IDBUserGroupsReader reader = new UserGroupsManager();
        return reader;
    }

    public List<UserGroups> getUserGroups(UserGroupsExample example){
        logger.entry(example);
        List<UserGroups> userGroups = userGroupsMapper.selectByExample(example);
        logger.exit(userGroups);
        return userGroups;
    }

    public void insert(UserGroups userGroup){
        logger.entry(userGroup);
        userGroupsMapper.insert(userGroup);
    }
    public void updateByPrimaryKey(UserGroups userGroup){
        logger.entry(userGroup);
        userGroupsMapper.updateByPrimaryKey(userGroup);
    }
    public UserGroups selectByPrimaryKey(Long id){
        logger.entry(id);
        UserGroups userGroups =  userGroupsMapper.selectByPrimaryKey(id);
        logger.exit(userGroups);
        return userGroups;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        userGroupsMapper.deleteByPrimaryKey(id);
    }
}
