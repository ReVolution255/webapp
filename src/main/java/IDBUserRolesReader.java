package main.java;

import java.util.List;

public interface IDBUserRolesReader {
    List<UserRoles> getUserRoles(UserRolesExample example);
    void insert(UserRoles userRole);
    void updateByPrimaryKey(UserRoles userRole);
    UserRoles selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
