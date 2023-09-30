package ru.Pavel.Spring.Controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Domain.Exceptions.BadSegmentException;
import ru.Pavel.Domain.Exceptions.InvalidPercentException;
import ru.Pavel.Domain.Exceptions.UserAlreadyHaveSegmentException;
import ru.Pavel.Services.SegmentService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/segment")
public class SegmentController {
    Gson jsonSerializer = new Gson();
    private SegmentService segmentService;
    @Autowired
    public SegmentController(SegmentService service){
        this.segmentService = service;
    }
    @GetMapping()
    public ResponseEntity<String> handleGetAllSegments(){
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonSerializer.toJson(segmentService.getAllSegments()));
    }

    @PostMapping()
    public ResponseEntity<?> handleCreateSegment(@RequestBody Segment segment){
        segmentService.createSegment(segment);
        return ResponseEntity.ok("");
    }

    @PostMapping("/p")
    public ResponseEntity<?> handleCreateSegmentWithPercent(@RequestBody Segment segment, @RequestHeader("percent") double percent){
        try {
            segmentService.createSegment(segment,percent);
            return ResponseEntity.ok("");
        }catch (UserAlreadyHaveSegmentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (BadSegmentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (InvalidPercentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping()
    public ResponseEntity<?> handleDeleteSegment(@RequestBody Segment segment){
        segmentService.deleteSegment(segment);
        return ResponseEntity.ok("");
    }

}


