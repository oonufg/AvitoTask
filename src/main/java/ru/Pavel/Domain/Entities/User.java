package ru.Pavel.Domain.Entities;

import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Domain.Exceptions.UserNotHaveSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.UserSegmentRepository;

import java.util.List;

public class User {
    private long id;
    private static UserSegmentRepository segments;

    static{
        segments = new UserSegmentRepository();
    }

    public User(long userId){
        this.id = userId;
    }

    public List<Segment> getSegments() {
        return segments.getUserSegments(this);
    }

    public void addSegments(List<Segment> segment)throws BadSegmentException, UserAlreadyHaveSegmentException {
        segments.addSegmentsToUser(this, segment);
    }

    public void deleteSegments(List<Segment> segment)  throws  BadSegmentException, UserNotHaveSegmentException {
        segments.deleteSegmentsFromUser(this, segment);
    }

    public long getId(){
        return id;
    }

}
