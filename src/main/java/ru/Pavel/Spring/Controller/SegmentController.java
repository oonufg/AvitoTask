package ru.Pavel.Spring.Controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Pavel.Domain.Entities.Segment;
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

    @DeleteMapping()
    public ResponseEntity<?> handleDeleteSegment(@RequestBody Segment segment){
        segmentService.deleteSegment(segment);
        return ResponseEntity.ok("");
    }

}
