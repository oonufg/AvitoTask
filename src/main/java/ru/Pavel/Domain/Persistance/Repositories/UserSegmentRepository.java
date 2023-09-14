package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.User;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.SegmentMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.UserSegmentTable;

import java.util.List;

public class UserSegmentRepository {

    private static UserSegmentTable userSegmentTable;

    static{
        userSegmentTable = new UserSegmentTable();
    }

    public void addSegmentsToUser(User user, List<Segment> segments ){
        userSegmentTable.addSegmentToUser(user.getId(), segments.get(0).getId());
    }

    public void deleteSegmentsFromUser(User user, List<Segment> segments ){

    }

    public List<Segment> getUserSegments(User user){
        List<Segment> result = SegmentMapper.mapListOfSegments(userSegmentTable.getUserSegments(user.getId()));
        return result;
    }

}
