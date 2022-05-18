package tokyo.boblennon.bcnjob22.application;

import java.util.List;

import tokyo.boblennon.bcnjob22.core.mappers.dtos.GetUserDto;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.User;

public interface UserApplication {
    public GetUserDto addUser(PostUserDto postUserDto);
    // not asked to be implemented in this challenge
    // public GetUserDto updateUser(PostUserDto postUserDto);
    // public void deleteUser(String id);

    public void addRoleToUser(String userName, String roleName);

    // public GetUserDto getUser(User user);
    public List<User> getAllUsers();
}
