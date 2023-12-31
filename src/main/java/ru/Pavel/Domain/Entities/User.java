package ru.Pavel.Domain.Entities;

import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Domain.Exceptions.UserNotHaveSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.UserSegmentRepository;

import java.util.List;

public class User {
    private long id;
    private static UserSegmentRepository segmentRepository;

    static{
        segmentRepository = new UserSegmentRepository();
    }

    public User(long userId){
        this.id = userId;
    }

    public List<Segment> getSegments() {
        return segmentRepository.getUserSegments(this);
    }
    public List<UserSegment> getSegmentsHistory(){
        return segmentRepository.getUserSegmentsHistory(this);
    }

    public void addSegments(List<Segment> segmentList, Long expired_timestamp)throws BadSegmentException, UserAlreadyHaveSegmentException {
        segmentRepository.addSegmentsToUser(this, segmentList, expired_timestamp);
    }
    
    public void addSegment(Segment segment, Long expired_timestamp) throws  BadSegmentException, UserAlreadyHaveSegmentException{
        segmentRepository.addSegmentToUser(this, segment, expired_timestamp);
    } 

    public void deleteSegments(List<Segment> segment)  throws  BadSegmentException, UserNotHaveSegmentException {
        segmentRepository.deleteSegmentsFromUser(this, segment);
    }

    public long getId(){
        return id;
    }

}
