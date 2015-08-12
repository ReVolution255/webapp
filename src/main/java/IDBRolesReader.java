package main.java;

import java.util.List;

public interface IDBRolesReader {
    List<Roles> getRoles(RolesExample example);
    void insert(Roles role);
    void updateByPrimaryKey(Roles role);
    Roles selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
