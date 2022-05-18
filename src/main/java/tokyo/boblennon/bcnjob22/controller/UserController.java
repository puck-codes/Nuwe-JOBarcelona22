package tokyo.boblennon.bcnjob22.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tokyo.boblennon.bcnjob22.application.UserApplicationImp;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;

@RestController
public class UserController {

    private final UserApplicationImp userApplicationImp;

    @Autowired
    public UserController(final UserApplicationImp userApplicationImp) {
        this.userApplicationImp = userApplicationImp;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> add(@RequestBody PostUserDto postUserDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/signup").toUriString());
        this.userApplicationImp.addUser(postUserDto);
        return ResponseEntity.created(uri).body(getJWTToken(postUserDto));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(201).body(this.userApplicationImp.getAllUsers());
    }

    // This logic is used three times in different places, it should be refactored to a 'Utility' package
    private String getJWTToken(PostUserDto postUserDto) {
        Algorithm algorithm = Algorithm.HMAC256("bcn22".getBytes());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        postUserDto.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        String access_token = JWT.create()
                .withSubject(postUserDto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .sign(algorithm);
        return access_token;
    }
}