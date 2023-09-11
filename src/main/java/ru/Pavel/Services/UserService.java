package ru.Pavel.Services;

import org.springframework.stereotype.Service;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Persistance.Repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private static UserRepository users;

    static{
        users = new UserRepository();
    }

    public UserService(){

    }

    public void createUser(){
        users.createUser();
    }

    public void deleteUser(long user_id){
        users.deleteUser(user_id);
    }

    public List<Segment> getUserSegments(long user_id){
        User user = users.getUser(user_id);
        return user.getSegments();
    }


}
