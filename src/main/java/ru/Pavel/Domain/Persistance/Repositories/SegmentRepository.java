package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Persistance.Repositories.Mapper.SegmentMapper;
import ru.Pavel.Domain.Persistance.Repositories.Tables.SegmentTable;

import java.util.List;

public class SegmentRepository {
    private static SegmentTable segmentTable;

    static{
        segmentTable = new SegmentTable();
    }

    public void appendSegment(Segment segment){
        segmentTable.createSegment(segment.getSlug());
    }
    public void deleteSegment(Segment segment){
        segmentTable.deleteSegment(segment.getSlug());

    }

    public List<Segment> getAllSegments(){
        List<Segment> result = SegmentMapper.mapListOfSegments(segmentTable.getAllSegments());
        return result;
    }

}
