package ru.Pavel.Domain.Persistance.Repositories.Mapper;

import ru.Pavel.Domain.Entities.Segment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SegmentMapper {
    public static Segment mapSegment(Map<String,Object> unmappedSegment){
        Segment segment = new Segment((Long)unmappedSegment.get("id"),(String)unmappedSegment.get("slug"));
        return segment;
    }

    public static List<Segment> mapListOfSegments(List<Map<String,Object>> unmappedListOfSegments){
        List<Segment> result = new ArrayList<>();
        for(Map<String,Object> unmappedSegment: unmappedListOfSegments){
            result.add(mapSegment(unmappedSegment));
        }
        return result;
    }
}
