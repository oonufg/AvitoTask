package ru.Pavel.Services;

import org.springframework.stereotype.Service;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
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

    public void createSegment(Segment segment, double percent) throws BadSegmentException, UserAlreadyHaveSegmentException {
        segmentRepository.appendSegment(segment);
        Segment currentSegment = segmentRepository.getCertainSegment(segment.getSlug());

        List<User> userList = userRepository.getAllUsers();
        Collections.shuffle(userList);
        int numberOfPeopleToAdd = (int)(userList.size() * (percent / 100));
        for(int i = 0; i < numberOfPeopleToAdd; i++){
            User currentUser = userList.get(i);
            currentUser.addSegment(currentSegment);
        }
    }

    public void deleteSegment(Segment segment){
        segmentRepository.deleteSegment(segment);
    }
    public List<Segment> getAllSegments(){
        return segmentRepository.getAllSegments();
    }


}
