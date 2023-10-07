package ru.Pavel.Domain.Persistance.Repositories;

import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Persistance.Mapper.SegmentMapper;
import ru.Pavel.Domain.Persistance.Tables.SegmentTable;

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

    public Segment getCertainSegment(String slug){
        Segment segment = SegmentMapper.mapSegment(segmentTable.getCertainSegment(slug));
        return segment;
    }

    public boolean isSegmentExist(Segment segment){
        return segmentTable.isSegmentExist(segment.getId(),segment.getSlug());
    }

}
