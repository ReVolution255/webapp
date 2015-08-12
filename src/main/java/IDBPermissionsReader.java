package main.java;

import java.util.List;

public interface IDBPermissionsReader {
    List<Permissions> getPermissions(PermissionsExample example);
    void insert(Permissions permission);
    void updateByPrimaryKey(Permissions permission);
    Permissions selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
