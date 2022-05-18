package tokyo.boblennon.bcnjob22.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tokyo.boblennon.bcnjob22.core.EntityBase;

@Document(collection = "roles")
public @Getter @Setter @NoArgsConstructor @AllArgsConstructor class Role extends EntityBase {
    private String name;
}
