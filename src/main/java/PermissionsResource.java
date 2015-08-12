package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/permissions/")
@Component
@Produces("application/json")
public class PermissionsResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBPermissionsReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBPermissionsReader manager) {
        this.manager = manager;
    }

    private main.java.IDBPermissionsReader manager;

    @GET
    public List<Permissions> getPermissions(){
        logger.entry();
        List<Permissions> permissions = manager.getPermissions(null);
        logger.exit(permissions);
        return permissions;
    }
    @GET @Path("{id}")
    public Permissions getPermission(@PathParam("id") long id){
        logger.entry(id);
        Permissions permission = manager.selectByPrimaryKey(id);
        logger.exit(permission);
        return permission;
    }
    @POST @Consumes("application/json")
    public List<Permissions> addPermission(Permissions permission){
        logger.entry(permission);
        manager.insert(permission);
        List<Permissions> permissions = manager.getPermissions(null);
        logger.exit(permissions);
        return permissions;
    }
    @PUT @Consumes("application/json")
    public void updatePermission(Permissions permission){
        logger.entry(permission);
        manager.updateByPrimaryKey(permission);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteUser(Users user){
        logger.entry(user);
        manager.deleteByPrimaryKey(user.getId());
        logger.exit();
    }
}
