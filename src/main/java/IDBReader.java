package main.java;

import java.util.List;

public interface IDBReader {
    List<Users> getUsers(UsersExample example);
    void insert(Users user);
    void updateByPrimaryKey(Users user);
    Users selectByPrimaryKey(Long id);
    void deleteByPrimaryKey(Long id);
}
