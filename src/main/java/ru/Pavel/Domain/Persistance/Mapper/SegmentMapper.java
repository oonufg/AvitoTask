package ru.Pavel.Domain.Persistance.Mapper;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Entities.UserSegment;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SegmentMapper {
    public static Segment mapSegment(Map<String,Object> unmappedSegment){
        Segment segment = new Segment(
                (Long)unmappedSegment.get("id"),
                (String)unmappedSegment.get("slug")
        );
        return segment;
    }

    public static UserSegment mapUserSegment(Map<String, Object> unmappedUserSegments){
        UserSegment.UserSegmentBuilder builder = new UserSegment.UserSegmentBuilder(
                (Long)unmappedUserSegments.get("id"),
                (String)unmappedUserSegments.get("slug"),
                (String)unmappedUserSegments.get("action"),
                ((Timestamp)unmappedUserSegments.get("timestamp")).getTime()
        );
        if((Long)unmappedUserSegments.get("expired_timestamp") != null){
            builder.setExpiredDate(((Timestamp)unmappedUserSegments.get("timestamp")).getTime());
        }
        return builder.build();
    }

    public static List<Segment> mapListOfSegments(List<Map<String,Object>> unmappedListOfSegments){
        List<Segment> result = new ArrayList<>();
        for(Map<String,Object> unmappedSegment: unmappedListOfSegments){
            result.add(mapSegment(unmappedSegment));
        }
        return result;
    }

    public static List<UserSegment> mapListOfUserSegments(List<Map<String,Object>> unmappedListOfUserSegments){
        List<UserSegment> result = new ArrayList<>();
        for(Map<String, Object> unmappedUserSegments: unmappedListOfUserSegments){
            result.add(mapUserSegment(unmappedUserSegments));
        }
        return result;
    }


}