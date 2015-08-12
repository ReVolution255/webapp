package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/groups/")
@Component
@Produces("application/json")
public class GroupsResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBGroupsReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBGroupsReader manager) {
        this.manager = manager;
    }

    private main.java.IDBGroupsReader manager;

    @GET
    public List<Groups> getGroups(){
        logger.entry();
        List<Groups> groups = manager.getGroups(null);
        logger.exit(groups);
        return groups;
    }
    @GET @Path("{id}")
    public Groups getGroup(@PathParam("id") long id){
        logger.entry(id);
        Groups group = manager.selectByPrimaryKey(id);
        logger.exit(group);
        return group;
    }
    @POST @Consumes("application/json")
    public List<Groups> addGroup(Groups group){
        logger.entry(group);
        manager.insert(group);
        List<Groups> groups = manager.getGroups(null);
        logger.exit(groups);
        return groups;
    }
    @PUT @Consumes("application/json")
    public void updateGroup(Groups group){
        logger.entry(group);
        manager.updateByPrimaryKey(group);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteGroup(Groups group){
        logger.entry(group);
        manager.deleteByPrimaryKey(group.getId());
        logger.exit();
    }
}
