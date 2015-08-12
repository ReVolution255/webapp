package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/usergroups/")
@Component
@Produces("application/json")
public class UserGroupsResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBUserGroupsReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBUserGroupsReader manager) {
        this.manager = manager;
    }

    private main.java.IDBUserGroupsReader manager;

    @GET
    public List<UserGroups> getUserGroups(){
        logger.entry();
        List<UserGroups> userGroups = manager.getUserGroups(null);
        logger.exit(userGroups);
        return userGroups;
    }
    @GET @Path("{id}")
    public UserGroups getUserGroup(@PathParam("id") long id){
        logger.entry(id);
        UserGroups userGroup = manager.selectByPrimaryKey(id);
        logger.exit(userGroup);
        return userGroup;
    }
    @POST @Consumes("application/json")
    public List<UserGroups> addUserGroup(UserGroups userGroup){
        logger.entry(userGroup);
        manager.insert(userGroup);
        List<UserGroups> userGroups = manager.getUserGroups(null);
        logger.exit(userGroups);
        return userGroups;
    }
    @PUT @Consumes("application/json")
    public void updateUserGroup(UserGroups userGroup){
        logger.entry(userGroup);
        manager.updateByPrimaryKey(userGroup);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteUserGroup(UserGroups userGroup){
        logger.entry(userGroup);
        manager.deleteByPrimaryKey(userGroup.getId());
        logger.exit();
    }
}
