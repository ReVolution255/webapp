package main.java;
import com.google.inject.Guice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;

import java.util.List;

@Path("/")
@Produces("application/json")
public class UsersResource {
    private static final Logger logger = LogManager.getLogger();
    private main.java.IBDReader manager = Guice.createInjector(new main.java.BDModule()).getInstance(main.java.IBDReader.class);
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
    public List<Users> updateUser(Users user){
        logger.entry(user);
        manager.updateByPrimaryKey(user);
        List<Users> users = manager.getUsers(null);
        logger.exit(users);
        return users;
    }
    @DELETE @Consumes("application/json")
    public List<Users> deleteUser(Users user){
        logger.entry(user);
        manager.deleteByPrimaryKey(user.getId());
        List<Users> users = manager.getUsers(null);
        logger.exit(users);
        return users;
    }
}
