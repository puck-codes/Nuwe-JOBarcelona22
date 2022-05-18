package tokyo.boblennon.bcnjob22.application;

import java.util.List;

import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.User;

public interface UserApplication {
    public User addUser(PostUserDto postUserDto);
    // These methods were not asked to be implemented in this challenge
    // public GetUserDto updateUser(PostUserDto postUserDto);
    // public void deleteUser(String id);

    public void addRoleToUser(String userName, String roleName);

    // public GetUserDto getUser(User user);
    public List<User> getAllUsers();
}
