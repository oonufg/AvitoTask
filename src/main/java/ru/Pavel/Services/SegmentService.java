package ru.Pavel.Services;

import org.springframework.stereotype.Service;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Persistance.Repositories.SegmentRepository;

import java.util.List;

@Service
public class SegmentService {

    private static SegmentRepository segments;

    static{
        segments = new SegmentRepository();
    }

    public SegmentService(){
        segments = new SegmentRepository();
    }
    public void createSegment(Segment segment){
        segments.appendSegment(segment);
    }

    public void deleteSegment(Segment segment){
        segments.deleteSegment(segment);
    }
    public List<Segment> getAllSegments(){
        return segments.getAllSegments();
    }
    public List<Segment> getConcreteSegments(List<String> slugs){
        return null;
    }
}
