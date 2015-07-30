import javax.ws.rs.*;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import main.java.Users;
import main.java.UsersExample;
import main.java.UsersMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@Path("/users/")
@Produces("text/plain")
@Provider
public class UsersResource {
    SqlSession session;

    @GET @Path("{id}")
    public String getUser(@DefaultValue("-1") @PathParam("id") long id){
        UsersExample query;
        if (id == -1){
            query = null;
        } else {
            query = new UsersExample();
            query.createCriteria().andIdEqualTo(id);
        }
        return getUsersAsJSON(query);
    }
    @POST @Consumes("application/x-www-form-urlencoded")
    public String addUser(@DefaultValue("unnamed") @FormParam("name") String name){
        Users newUser = new Users();
        newUser.setName(name);
        getMapper().insert(newUser);

        session.commit();
        session.close();
        return getUsersAsJSON(null);
    }
    @PUT @Path("{id}/{name}")
    public String updateUser(@DefaultValue("-1") @PathParam("id") long id,
                             @DefaultValue("unnamed") @PathParam("name") String name){
        Users editedUser = new Users();
        if (id != -1) {
            editedUser.setName(name);
            editedUser.setId(id);
            getMapper().updateByPrimaryKey(editedUser);
        }

        session.commit();
        session.close();
        return getUsersAsJSON(null);
    }
    @DELETE @Path("{id}")
    public String deleteUser(@DefaultValue("-1") @PathParam("id") long id){
        if (id != -1)
        getMapper().deleteByPrimaryKey(id);

        session.commit();
        session.close();
        return getUsersAsJSON(null);
    }

    public UsersMapper getMapper(){
        session = main.java.SessionManager.getSession();;
        return session.getMapper(UsersMapper.class);
    }

    public String getUsersAsJSON(UsersExample example){
        List<Users> users = getMapper().selectByExample(example);
        return new Gson().toJson(users);
    }
}
