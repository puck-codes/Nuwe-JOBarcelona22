package tokyo.boblennon.bcnjob22.core.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import tokyo.boblennon.bcnjob22.core.mappers.dtos.GetUserDto;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Mapping from User to DTO
    PostUserDto toPostUserDto(User user);
    GetUserDto toGetUserDto(User user);

    // Mapping from DTO to User
    User toUser(PostUserDto userDto);
    User toUser(GetUserDto getUserDto);

    // Mapping from User's List to DTO's List
    List<GetUserDto> toGetUserDtos(List<User> users);
}
