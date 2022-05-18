package tokyo.boblennon.bcnjob22.core.mappers.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public @Getter @Setter @NoArgsConstructor class GetUserDto {
    private UUID id;
    private String username;
    private String email;
}
