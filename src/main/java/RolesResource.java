package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/roles/")
@Component
@Produces("application/json")
public class RolesResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBRolesReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBRolesReader manager) {
        this.manager = manager;
    }

    private main.java.IDBRolesReader manager;

    @GET
    public List<Roles> getRoles(){
        logger.entry();
        List<Roles> roles = manager.getRoles(null);
        logger.exit(roles);
        return roles;
    }
    @GET @Path("{id}")
    public Roles getRoles(@PathParam("id") long id){
        logger.entry(id);
        Roles roles = manager.selectByPrimaryKey(id);
        logger.exit(roles);
        return roles;
    }
    @POST @Consumes("application/json")
    public List<Roles> addRole(Roles role){
        logger.entry(role);
        manager.insert(role);
        List<Roles> roles = manager.getRoles(null);
        logger.exit(roles);
        return roles;
    }
    @PUT @Consumes("application/json")
    public void updateRole(Roles role){
        logger.entry(role);
        manager.updateByPrimaryKey(role);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteRole(Roles role){
        logger.entry(role);
        manager.deleteByPrimaryKey(role.getId());
        logger.exit();
    }
}
