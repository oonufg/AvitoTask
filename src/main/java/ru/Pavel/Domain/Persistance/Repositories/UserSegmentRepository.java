package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.SegmentMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.SegmentTable;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserSegmentTable;

import java.util.List;

public class UserSegmentRepository {

    private static UserSegmentTable userSegmentTable;
    private static SegmentTable segmentTable;

    static{
        userSegmentTable = new UserSegmentTable();
        segmentTable = new SegmentTable();
    }

    public void addSegmentsToUser(User user, List<Segment> segments ) throws  BadSegmentException{
        if(isSegmentValid(segments.get(0))) {
            userSegmentTable.addSegmentToUser(user.getId(), segments.get(0).getId());
        }
        else{
            throw new BadSegmentException("OhNO");
        }

    }

    public void deleteSegmentsFromUser(User user, List<Segment> segments ){

    }

    public List<Segment> getUserSegments(User user){
        List<Segment> result = SegmentMapper.mapListOfSegments(userSegmentTable.getUserSegments(user.getId()));
        return result;
    }

    private boolean isSegmentValid(Segment segmentToCheck){
        if(segmentTable.isSegmentExist(segmentToCheck.getId(),segmentToCheck.getSlug())){
            return true;
        }
        return false;
    }

}
