package tokyo.boblennon.bcnjob22.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import tokyo.boblennon.bcnjob22.application.UserApplicationImp;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.User;

@RestController
@SecurityRequirement(name = "bcn22")
public class UserController {

    private final UserApplicationImp userApplicationImp;

    @Autowired
    public UserController(final UserApplicationImp userApplicationImp) {
        this.userApplicationImp = userApplicationImp;
    }

    @Operation(summary = "Create a new user in the database")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "User created", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input", 
          content = @Content)})

    @PostMapping(path = "/signup")
    public ResponseEntity<?> add(@RequestBody PostUserDto postUserDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/signup").toUriString());
        Map<String, String> token = new HashMap<>();
        token.put("access_token", getJWTToken(this.userApplicationImp.addUser(postUserDto)));
        return ResponseEntity.created(uri).body(token);
    }

    @Operation(summary = "Retrieves all existing users in the database except for the administrator users. ")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Successful operation", 
          content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "403", description = "Access Denied", 
          content = @Content)})

    @GetMapping(path = "/users")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(this.userApplicationImp.getAllUsers());
    }

    // This logic is used three times in different places, it should be refactored to a 'Utility' package
    private String getJWTToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256("bcn22".getBytes());
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", authorities.stream().collect(Collectors.toList()))
                .sign(algorithm);
        return access_token;
    }
}