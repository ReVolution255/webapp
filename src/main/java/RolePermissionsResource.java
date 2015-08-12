package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/rolepermissions/")
@Component
@Produces("application/json")
public class RolePermissionsResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBRolePermissionsReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBRolePermissionsReader manager) {
        this.manager = manager;
    }

    private main.java.IDBRolePermissionsReader manager;

    @GET
    public List<RolePermissions> getRolePermissions(){
        logger.entry();
        List<RolePermissions> rolePermissions = manager.getRolePermissions(null);
        logger.exit(rolePermissions);
        return rolePermissions;
    }
    @GET @Path("{id}")
    public RolePermissions getRolePermission(@PathParam("id") long id){
        logger.entry(id);
        RolePermissions rolePermission = manager.selectByPrimaryKey(id);
        logger.exit(rolePermission);
        return rolePermission;
    }
    @POST @Consumes("application/json")
    public List<RolePermissions> addRolePermissions(RolePermissions rolePermission){
        logger.entry(rolePermission);
        manager.insert(rolePermission);
        List<RolePermissions> rolePermissions = manager.getRolePermissions(null);
        logger.exit(rolePermissions);
        return rolePermissions;
    }
    @PUT @Consumes("application/json")
    public void updateRolePermission(RolePermissions rolePermission){
        logger.entry(rolePermission);
        manager.updateByPrimaryKey(rolePermission);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteRolePermission(RolePermissions rolePermission){
        logger.entry(rolePermission);
        manager.deleteByPrimaryKey(rolePermission.getId());
        logger.exit();
    }
}
