package tokyo.boblennon.bcnjob22.core.mappers.dtos;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.bcnjob22.core.annotations.PasswordConstraint;
import tokyo.boblennon.bcnjob22.domain.Role;

@Validated
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor class PostUserDto {

    @NotBlank
    private String username;

    @Email
    private String email;

    // Custom validation annotation
    @PasswordConstraint
    private String password;

    private Collection<Role> roles = new ArrayList<>();
}
