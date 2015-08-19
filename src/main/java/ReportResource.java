package main.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/report/")
@Component
@Produces("application/json")
public class ReportResource {
    private static final Logger logger = LogManager.getLogger();

    private main.java.IDBReader manager;
    private main.java.IDBUserRolesReader userRolesManager;
    private main.java.IDBUserGroupsReader userGroupsManager;
    private main.java.IDBRolePermissionsReader rolePermissionsManager;
    private main.java.IDBGroupsReader groupsManager;
    private main.java.IDBPermissionsReader permissionsManager;

    public void setManager(main.java.IDBReader manager) {
        this.manager = manager;
    }

    public void setUserRolesManager(main.java.IDBUserRolesReader userRolesManager) {
        this.userRolesManager = userRolesManager;
    }

    public void setUserGroupsManager(main.java.IDBUserGroupsReader userGroupsManager) {
        this.userGroupsManager = userGroupsManager;
    }

    public void setRolePermissionsManager(main.java.IDBRolePermissionsReader rolePermissionsManager) {
        this.rolePermissionsManager = rolePermissionsManager;
    }

    public void setGroupsManager(main.java.IDBGroupsReader groupsManager) {
        this.groupsManager = groupsManager;
    }

    public void setPermissionsManager(main.java.IDBPermissionsReader permissionsManager) {
        this.permissionsManager = permissionsManager;
    }

    @GET @Path("/groupusers/{id}")
    public Set<Users> getGroupUsers(@PathParam("id") long id){
        logger.entry(id);
        Set<Users> users = new HashSet<Users>();
        Set<Groups> allGroups = new HashSet<Groups>();
        allGroups.add(groupsManager.selectByPrimaryKey(id));
        List<Groups> groups = groupsManager.getGroups(null);
        List<UserGroups> userGroups = userGroupsManager.getUserGroups(null);
        findSubGroups(id, groups, allGroups);
        for (UserGroups item : userGroups){
            for (Groups group : allGroups) {
                if (item.getGroup_id().longValue() == group.getId().longValue()) users.add(manager.selectByPrimaryKey(item.getUser_id()));
            }
        }
        logger.exit(users);
        return users;
    }
    @GET @Path("/userpermissions/{id}")
    public Set<Permissions> getUserPermissions(@PathParam("id") long id){
        logger.entry(id);
        List<UserRoles> userRoles = userRolesManager.getUserRoles(null);
        List<RolePermissions> rolePermissions = rolePermissionsManager.getRolePermissions(null);
        List<UserRoles> currentUserRoles = new ArrayList<UserRoles>();
        for (UserRoles role : userRoles) {
            if (role.getUser_id() == id) currentUserRoles.add(role);
        }
        List<RolePermissions> currentUserRolePermissions = new ArrayList<RolePermissions>();
        for (UserRoles userRole : currentUserRoles){
            for (RolePermissions item : rolePermissions){
                if (item.getRole_id().longValue() == userRole.getRole_id().longValue()) currentUserRolePermissions.add(item);
            }
        }
        List<Permissions> permissions = permissionsManager.getPermissions(null);
        Set<Permissions> userPermissions = new HashSet<Permissions>();
        for (RolePermissions item : currentUserRolePermissions){
            Permissions permission = permissionsManager.selectByPrimaryKey(item.getPermission_id());
            userPermissions.add(permission);
        }
        logger.exit(userPermissions);
        return userPermissions;
    }

    public void findSubGroups(long group_id, List<Groups> groups, Set<Groups> allGroups){
        for (Groups group : groups){
            if (group.getParent_id() != null && group.getParent_id() == group_id) {
                allGroups.add(group);
                findSubGroups(group.getId(), groups, allGroups);
            }
        }
    }
}
