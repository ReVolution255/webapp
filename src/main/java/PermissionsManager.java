package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsManager implements main.java.IDBPermissionsReader {
    private static final Logger logger = LogManager.getLogger();

    public PermissionsMapper getPermissionsMapper() {
        return permissionsMapper;
    }
    @Autowired
    private PermissionsMapper permissionsMapper;

    public void setPermissionsMapper(PermissionsMapper permissionsMapper) {
        this.permissionsMapper = permissionsMapper;
    }

    public static main.java.IDBPermissionsReader daoFactory(){
        main.java.IDBPermissionsReader reader = new PermissionsManager();
        return reader;
    }

    public List<Permissions> getPermissions(PermissionsExample example){
        logger.entry(example);
        List<Permissions> permissions = permissionsMapper.selectByExample(example);
        logger.exit(permissions);
        return permissions;
    }

    public void insert(Permissions permission){
        logger.entry(permission);
        permissionsMapper.insert(permission);
    }
    public void updateByPrimaryKey(Permissions permission){
        logger.entry(permission);
        permissionsMapper.updateByPrimaryKey(permission);
    }
    public Permissions selectByPrimaryKey(Long id){
        logger.entry(id);
        Permissions permission =  permissionsMapper.selectByPrimaryKey(id);
        logger.exit(permission);
        return permission;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        permissionsMapper.deleteByPrimaryKey(id);
    }
}
