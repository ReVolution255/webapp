package main.java;

import java.util.List;

public interface IDBUserGroupsReader {
    List<UserGroups> getUserGroups(UserGroupsExample example);
    void insert(UserGroups userGroup);
    void updateByPrimaryKey(UserGroups userGroup);
    UserGroups selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
