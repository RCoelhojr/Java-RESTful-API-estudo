package me.dio.Controller;

import me.dio.domain.model.User;
import me.dio.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
       var user = userService.findById(id);
       return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody User userToCreate) {
        var userCreated = userService.create(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(((User) userCreated).getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }
}