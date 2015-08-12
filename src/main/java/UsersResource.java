package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.List;

@Path("/users/")
@Component
@Produces("application/json")
public class UsersResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBReader manager) {
        this.manager = manager;
    }

    private main.java.IDBReader manager;

    @GET
    public List<Users> getUsers(){
        logger.entry();
        List<Users> users = manager.getUsers(null);
        logger.exit(users);
        return users;
    }
    @GET @Path("{id}")
    public Users getUser(@PathParam("id") long id){
        logger.entry(id);
        Users user = manager.selectByPrimaryKey(id);
        logger.exit(user);
        return user;
    }
    @POST @Consumes("application/json")
    public List<Users> addUser(Users user){
        logger.entry(user);
        manager.insert(user);
        List<Users> users = manager.getUsers(null);
        logger.exit(users);
        return users;
    }
    @PUT @Consumes("application/json")
    public void updateUser(Users user){
        logger.entry(user);
        manager.updateByPrimaryKey(user);
        logger.exit();
    }
    @DELETE @Consumes("application/json")
    public void deleteUser(Users user){
        logger.entry(user);
        manager.deleteByPrimaryKey(user.getId());
        logger.exit();
    }
}
