package main.java;
import javax.ws.rs.*;

import java.util.List;

@Path("/")
@Produces("application/json")
public class UsersResource {
    @GET
    public List<Users> getUsers(){
        return new main.java.SessionManager().getUsers(null);
    }
    @GET @Path("{id}")
    public Users getUser(@PathParam("id") long id){
        return new main.java.SessionManager().selectByPrimaryKey(id);
    }
    @POST @Consumes("application/json")
    public List<Users> addUser(Users user){
        main.java.SessionManager manager = new main.java.SessionManager();
        manager.insert(user);
        return manager.getUsers(null);
    }
    @PUT @Consumes("application/json")
    public List<Users> updateUser(Users user){
        main.java.SessionManager manager = new main.java.SessionManager();
        manager.updateByPrimaryKey(user);
        return manager.getUsers(null);
    }
    @DELETE @Consumes("application/json")
    public List<Users> deleteUser(Users user){
        main.java.SessionManager manager = new main.java.SessionManager();
        manager.deleteByPrimaryKey(user.getId());
        return manager.getUsers(null);
    }
}
