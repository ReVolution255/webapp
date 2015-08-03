package main.java;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;

import java.util.List;

@Path("/")
@Produces("application/json")
public class UsersResource {
    private static final Logger logger = LogManager.getLogger();
    @GET
    public List<Users> getUsers(){
        logger.entry();
        logger.exit();
        return new main.java.UsersManager().getUsers(null);
    }
    @GET @Path("{id}")
    public Users getUser(@PathParam("id") long id){
        logger.entry(id);
        logger.exit();
        return new main.java.UsersManager().selectByPrimaryKey(id);
    }
    @POST @Consumes("application/json")
    public List<Users> addUser(Users user){
        logger.entry(user);
        main.java.UsersManager manager = new main.java.UsersManager();
        manager.insert(user);
        logger.exit();
        return manager.getUsers(null);
    }
    @PUT @Consumes("application/json")
    public List<Users> updateUser(Users user){
        logger.entry(user);
        main.java.UsersManager manager = new main.java.UsersManager();
        manager.updateByPrimaryKey(user);
        logger.exit();
        return manager.getUsers(null);
    }
    @DELETE @Consumes("application/json")
    public List<Users> deleteUser(Users user){
        logger.entry(user);
        main.java.UsersManager manager = new main.java.UsersManager();
        manager.deleteByPrimaryKey(user.getId());
        logger.exit();
        return manager.getUsers(null);
    }
}
