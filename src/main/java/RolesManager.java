package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesManager implements main.java.IDBRolesReader {
    private static final Logger logger = LogManager.getLogger();

    public RolesMapper getRolesMapper() {
        return rolesMapper;
    }
    @Autowired
    private RolesMapper rolesMapper;

    public void setRolesMapper(RolesMapper rolesMapper) {
        this.rolesMapper = rolesMapper;
    }

    public static main.java.IDBRolesReader daoFactory(){
        main.java.IDBRolesReader reader = new RolesManager();
        return reader;
    }

    public List<Roles> getRoles(RolesExample example){
        logger.entry(example);
        List<Roles> roles = rolesMapper.selectByExample(example);
        logger.exit(roles);
        return roles;
    }

    public void insert(Roles role){
        logger.entry(role);
        rolesMapper.insert(role);
    }
    public void updateByPrimaryKey(Roles role){
        logger.entry(role);
        rolesMapper.updateByPrimaryKey(role);
    }
    public Roles selectByPrimaryKey(Long id){
        logger.entry(id);
        Roles role =  rolesMapper.selectByPrimaryKey(id);
        logger.exit(role);
        return role;
    }
    public void deleteByPrimaryKey(Long id){
        logger.entry(id);
        rolesMapper.deleteByPrimaryKey(id);
    }
}
