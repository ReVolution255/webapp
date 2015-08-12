package main.java;

import java.util.List;

public interface IDBRolePermissionsReader {
    List<RolePermissions> getRolePermissions(RolePermissionsExample example);
    void insert(RolePermissions rolePermission);
    void updateByPrimaryKey(RolePermissions rolePermission);
    RolePermissions selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
