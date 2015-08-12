package main.java;

import java.util.List;

public interface IDBGroupsReader {
    List<Groups> getGroups(GroupsExample example);
    void insert(Groups group);
    void updateByPrimaryKey(Groups group);
    Groups selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
