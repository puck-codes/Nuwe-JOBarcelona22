package tokyo.boblennon.bcnjob22.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.bcnjob22.core.EntityBase;
import tokyo.boblennon.bcnjob22.core.annotations.PasswordConstraint;

@Document(collection = "users")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor class User extends EntityBase {

    @NotBlank
    private String username;

    @Email
    private String email;

    // Custom validation annotation
    @PasswordConstraint
    private String password;

    @DBRef
    private Collection<Role> roles = new ArrayList<>();
}
