package ru.Pavel.Services;

import org.springframework.stereotype.Service;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.InvalidPercentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.SegmentRepository;
import ru.Pavel.Domain.Persistance.Repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SegmentService {

    private static SegmentRepository segmentRepository;
    private  static UserRepository userRepository;

    static{
        segmentRepository = new SegmentRepository();
        userRepository = new UserRepository();
    }

    public SegmentService(){
        segmentRepository = new SegmentRepository();
    }
    public void createSegment(Segment segment){
        segmentRepository.appendSegment(segment);
    }

    public void createSegment(Segment segment, double percent, Long expiredTime) throws BadSegmentException, UserAlreadyHaveSegmentException, InvalidPercentException {
        segmentRepository.appendSegment(segment);
        Segment  newSegment = segmentRepository.getCertainSegment(segment.getSlug());
        addNewSegmentToPercentOfUsers(newSegment, percent, expiredTime);
    }

    public void deleteSegment(Segment segment){
        segmentRepository.deleteSegment(segment);
    }
    public List<Segment> getAllSegments(){
        return segmentRepository.getAllSegments();
    }

    private int calculateUserCount(int userCount, double percent) throws InvalidPercentException{
        double calculatedPercent = percent / 100;
        if(calculatedPercent == 0 || calculatedPercent > 1){
            throw new InvalidPercentException("Invalid");
        }
        return (int)(userCount * calculatedPercent);
    }

    private void addNewSegmentToPercentOfUsers(Segment segment, double percent, Long expired_timestamp) throws InvalidPercentException, UserAlreadyHaveSegmentException, BadSegmentException {
        List<User> userList = userRepository.getAllUsers();
        int numberOfPeopleToAdd = calculateUserCount(userList.size(), percent);
        Collections.shuffle(userList);
        for (int i = 0; i < numberOfPeopleToAdd; i++) {
            User currentUser = userList.get(i);
            currentUser.addSegment(segment, expired_timestamp);
        }
    }

}
