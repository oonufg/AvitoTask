package ru.Pavel.Services;

import org.springframework.stereotype.Service;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Entities.UserSegment;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Domain.Exceptions.UserNotFoundException;
import ru.Pavel.Domain.Exceptions.UserNotHaveSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private static UserRepository users;

    static{
        users = new UserRepository();
    }
    public void createUser(){
        users.createUser();
    }

    public void deleteUser(long user_id){
        users.deleteUser(user_id);
    }

    public List<Segment> getUserSegments(long user_id) throws UserNotFoundException {
        User user = users.getUser(user_id);
        return user.getSegments();
    }

    public List<UserSegment> getUserSegmentsHistory(long user_id) throws UserNotFoundException{
        User user = users.getUser(user_id);
        return user.getSegmentsHistory();
    }

    public void addSegmentsToUser(long user_id, List<Segment> segmentsToAdd,Long expired_timestamp) throws UserNotFoundException, BadSegmentException, UserAlreadyHaveSegmentException {
        User user = users.getUser(user_id);
        user.addSegments(segmentsToAdd, expired_timestamp);
    }

    public void deleteUserSegments(long user_id, List<Segment> segmentsToDelete)  throws UserNotFoundException, BadSegmentException, UserNotHaveSegmentException {
        User user = users.getUser(user_id);
        user.deleteSegments(segmentsToDelete);
    }
}
