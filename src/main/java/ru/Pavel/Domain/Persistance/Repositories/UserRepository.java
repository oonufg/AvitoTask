package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Exceptions.UserNotFoundException;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.UserMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserSegmentTable;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserTable;

import java.util.List;
import java.util.Map;

public class UserRepository {

    private static UserTable userTable;
    private static UserSegmentTable userSegmentTable;

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

    public User getUser(long user_id) throws UserNotFoundException{
        Map<String, Object> unmappedUser = userTable.getUser(user_id);
        if(unmappedUser.isEmpty()){
            throw new UserNotFoundException("User not exists");
        }
        return UserMapper.mapUser(unmappedUser);
    }

    public List<User> getAllUsers(){
        return UserMapper.mapUserList(userTable.getAllUsers());
    }

}
