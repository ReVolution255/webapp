package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/userroles/")
@Component
@Produces("application/json")
public class UserRolesResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBUserRolesReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBUserRolesReader manager) {
        this.manager = manager;
    }

    private main.java.IDBUserRolesReader manager;

    @GET
    public List<UserRoles> getUserRoles(){
        logger.entry();
        List<UserRoles> userRoles = manager.getUserRoles(null);
        logger.exit(userRoles);
        return userRoles;
    }
    @GET @Path("{id}")
    public UserRoles getUserRole(@PathParam("id") long id){
        logger.entry(id);
        UserRoles userRole = manager.selectByPrimaryKey(id);
        logger.exit(userRole);
        return userRole;
    }
    @POST @Consumes("application/json")
    public List<UserRoles> addUserRole(UserRoles userRole){
        logger.entry(userRole);
        manager.insert(userRole);
        List<UserRoles> userRoles = manager.getUserRoles(null);
        logger.exit(userRoles);
        return userRoles;
    }
    @PUT @Consumes("application/json")
    public void updateUserRole(UserRoles userRole){
        logger.entry(userRole);
        manager.updateByPrimaryKey(userRole);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteUserRole(UserRoles userRole){
        logger.entry(userRole);
        manager.deleteByPrimaryKey(userRole.getId());
        logger.exit();
    }
}
