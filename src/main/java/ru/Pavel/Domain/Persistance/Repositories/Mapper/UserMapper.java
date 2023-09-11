package ru.Pavel.Domain.Persistance.Repositories.Mapper;

import ru.Pavel.Domain.Entities.User;

import java.util.Map;

public class UserMapper {
    public static User mapUser(Map<String,Object> unmappedSegment){
        User user = new User((long)unmappedSegment.get("id"));
        return user;
    }
}
