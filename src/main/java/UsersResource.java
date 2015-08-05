package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.*;

import java.util.List;

@Path("/")
@Produces("application/json")
public class UsersResource {
    private static final Logger logger = LogManager.getLogger();

    public main.java.IDBReader getManager() {
        return manager;
    }

    public void setManager(main.java.IDBReader manager) {
        logger.info("setManager");logger.info("Manager is " + (manager != null));
        this.manager = manager;
    }

    private main.java.IDBReader manager;

    @GET
    public List<Users> getUsers(){
        logger.entry();
        logger.info(manager != null);
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
