package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionsManager implements main.java.IDBRolePermissionsReader {
    private static final Logger logger = LogManager.getLogger();

    public RolePermissionsMapper getRolePermissionsMapper() {
        return rolePermissionsMapper;
    }
    @Autowired
    private RolePermissionsMapper rolePermissionsMapper;

    public void setRolePermissionsMapper(RolePermissionsMapper rolePermissionsMapper) {
        this.rolePermissionsMapper = rolePermissionsMapper;
    }

    public static main.java.IDBRolePermissionsReader daoFactory(){
        main.java.IDBRolePermissionsReader reader = new RolePermissionsManager();
        return reader;
    }

    public List<RolePermissions> getRolePermissions(RolePermissionsExample example){
        logger.entry(example);
        List<RolePermissions> rolePermissions = rolePermissionsMapper.selectByExample(example);
        logger.exit(rolePermissions);
        return rolePermissions;
    }

    public void insert(RolePermissions rolePermission){
        logger.entry(rolePermission);
        rolePermissionsMapper.insert(rolePermission);
    }
    public void updateByPrimaryKey(RolePermissions rolePermission){
        logger.entry(rolePermission);
        rolePermissionsMapper.updateByPrimaryKey(rolePermission);
    }
    public RolePermissions selectByPrimaryKey(Long id){
        logger.entry(id);
        RolePermissions rolePermission =  rolePermissionsMapper.selectByPrimaryKey(id);
        logger.exit(rolePermission);
        return rolePermission;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        rolePermissionsMapper.deleteByPrimaryKey(id);
    }
}
