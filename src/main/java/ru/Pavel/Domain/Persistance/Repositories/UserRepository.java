package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.UserMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserTable;

import java.util.Map;

public class UserRepository {

    private static UserTable userTable;

    static {
        userTable = new UserTable();
    }

    public void createUser(){
        userTable.createUser();
    }

    public void deleteUser(User user){
        userTable.deleteUser(user.getId());
    }

    public void deleteUser(long user_id){
        userTable.deleteUser(user_id);
    }

    public User getUser(long user_id){
        Map<String, Object> unmappedUser = userTable.getUser(user_id);
        return UserMapper.mapUser(unmappedUser);

    }

}
