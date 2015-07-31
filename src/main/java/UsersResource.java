package main.java;
import javax.ws.rs.*;

import java.util.List;

@Path("/")
@Produces("application/json")
public class UsersResource {

    @GET
    public List<Users> getUsers(){
        return getUsers(null);
    }

    @GET @Path("{id}")
    public List<Users> getUser(@PathParam("id") long id){
        UsersExample query;
        if (id == -1){
            query = null;
        } else {
            query = new UsersExample();
            query.createCriteria().andIdEqualTo(id);
        }
        return getUsers(query);
    }
    @POST @Consumes("application/json")
    public List<Users> addUser(Users user){
        getMapper().insert(user);

        main.java.SessionManager.closeSession();
        return getUsers(null);
    }
    @PUT @Consumes("application/json")
    public List<Users> updateUser(Users user){
        getMapper().updateByPrimaryKey(user);

        main.java.SessionManager.closeSession();
        return getUsers(null);
    }
    @DELETE @Consumes("application/json")
    public List<Users> deleteUser(Users user){
        if (user.getId() != -1)
        getMapper().deleteByPrimaryKey(user.getId());

        main.java.SessionManager.closeSession();
        return getUsers(null);
    }



    public UsersMapper getMapper(){
        return main.java.SessionManager.getSession().getMapper(UsersMapper.class);
    }

    public List<Users> getUsers(UsersExample example){
        return getMapper().selectByExample(example);
    }
}
