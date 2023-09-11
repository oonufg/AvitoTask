package ru.Pavel.Spring.Controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Pavel.Domain.Entities.Segment;
import ru.Pavel.Services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private Gson jsonSerializer;
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
        this.jsonSerializer = new Gson();
    }
    @PostMapping
    public ResponseEntity<?> handleCreateUser(){
        userService.createUser();
        return ResponseEntity.ok("");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> handleDeleteUser(@PathVariable("id") long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("");
    }

    @GetMapping("{id}")
    public ResponseEntity<?> handleGetUserSegments(@PathVariable("id") long userId){
        List<Segment> userSegments = userService.getUserSegments(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonSerializer.toJson(userSegments));
    }

}
