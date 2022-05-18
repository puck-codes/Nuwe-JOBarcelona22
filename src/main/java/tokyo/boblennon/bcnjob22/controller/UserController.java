package tokyo.boblennon.bcnjob22.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import tokyo.boblennon.bcnjob22.application.UserApplicationImp;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;

@RestController
public class UserController {

    private final UserApplicationImp userApplicationImp;

    @Autowired
    public UserController(final UserApplicationImp userApplicationImp) {
        this.userApplicationImp = userApplicationImp;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, path = "/signup")
    public ResponseEntity<?> add(@RequestBody PostUserDto postUserDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/signup").toUriString());
        //! this.userApplicationImp.add(user);
        return ResponseEntity.created(uri).body("signup");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(200).body("login");
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(201).body(this.userApplicationImp.getAllUsers());
    }

}
