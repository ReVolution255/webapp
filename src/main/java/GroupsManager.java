package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsManager implements main.java.IDBGroupsReader {
    private static final Logger logger = LogManager.getLogger();

    public GroupsMapper getGroupsMapper() {
        return groupsMapper;
    }
    @Autowired
    private GroupsMapper groupsMapper;

    public void setGroupsMapper(GroupsMapper groupsMapper) {
        this.groupsMapper = groupsMapper;
    }

    public static main.java.IDBGroupsReader daoFactory(){
        main.java.IDBGroupsReader reader = new GroupsManager();
        return reader;
    }

    public List<Groups> getGroups(GroupsExample example){
        logger.entry(example);
        List<Groups> groups = groupsMapper.selectByExample(example);
        logger.exit(groups);
        return groups;
    }

    public void insert(Groups group){
        logger.entry(group);
        groupsMapper.insert(group);
    }
    public void updateByPrimaryKey(Groups group){
        logger.entry(group);
        groupsMapper.updateByPrimaryKey(group);
    }
    public Groups selectByPrimaryKey(Long id){
        logger.entry(id);
        Groups group =  groupsMapper.selectByPrimaryKey(id);
        logger.exit(group);
        return group;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        groupsMapper.deleteByPrimaryKey(id);
    }
}
