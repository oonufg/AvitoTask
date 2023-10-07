package ru.Pavel.Domain.Persistance.Mapper;

import ru.Pavel.Domain.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserMapper {
    public static User mapUser(Map<String,Object> unmappedUser){
        User user = new User((long)unmappedUser.get("id"));
        return user;
    }

    public static List<User> mapUserList(List<Map<String, Object>> unmappedUsers){
        List<User> users = new ArrayList<>();
        for(Map<String, Object> currentUnMappedUser: unmappedUsers){
            users.add(mapUser(currentUnMappedUser));
        }
        return users;
    }

}
