package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Entities.UserSegment;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Domain.Exceptions.UserNotHaveSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.Enums.ActionsWithSegments;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.SegmentMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.SegmentTable;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserSegmentTable;

import java.util.Calendar;
import java.util.List;

public class UserSegmentRepository {

    private static UserSegmentTable userSegmentTable;
    private static SegmentTable segmentTable;

    static{
        userSegmentTable = new UserSegmentTable();
        segmentTable = new SegmentTable();
    }

    public void addSegmentToUser(User user, Segment segment) throws  UserAlreadyHaveSegmentException{
        long currentTimestamp = Calendar.getInstance().getTimeInMillis();
        String action = ActionsWithSegments.ADDING.getTitle();
        if(!isUserAlreadyHaveSegment(user, segment)){
            userSegmentTable.addSegmentToUser(user.getId(), segment.getId(),action ,currentTimestamp);
        }
        else {
            throw new UserAlreadyHaveSegmentException("Already have");
        }
    }

    public void addSegmentsToUser(User user, List<Segment> segments ) throws  BadSegmentException, UserAlreadyHaveSegmentException{
        long currentTimestamp = Calendar.getInstance().getTimeInMillis();
        String action = ActionsWithSegments.ADDING.getTitle();
        for(Segment currentSegment: segments){
            if(isSegmentValid(currentSegment) ) {
                addSegmentToUser(user,currentSegment);
            }
            else{
                throw new BadSegmentException("Bad segment");
            }
        }
    }

    public void deleteSegmentsFromUser(User user, List<Segment> segments ) throws BadSegmentException, UserNotHaveSegmentException {
        for(Segment currentSegment: segments){
            if(isSegmentValid(currentSegment) ) {
                if(isUserAlreadyHaveSegment(user, currentSegment)){
                    userSegmentTable.deleteUserSegment(user.getId(), currentSegment.getId());
                }
                else {
                    throw new UserNotHaveSegmentException("Already have");
                }
            }
            else{
                throw new BadSegmentException("Bad segment");
            }
        }
    }

    public List<Segment> getUserSegments(User user){
        List<Segment> result = SegmentMapper.mapListOfSegments(userSegmentTable.getUserSegments(user.getId()));
        return result;
    }

    public List<UserSegment> getUserSegmentsHistory(User user){
        List<UserSegment> result = SegmentMapper.mapListOfUserSegments(userSegmentTable.getUserSegmentsHistory(user.getId()));
        return result;
    }

    private boolean isSegmentValid(Segment segmentToCheck){
        return segmentTable.isSegmentExist(segmentToCheck.getId(), segmentToCheck.getSlug());
    }

    private boolean isUserAlreadyHaveSegment(User user, Segment segment){
        return userSegmentTable.isUserHaveSegment(user.getId(), segment.getId());
    }
}
