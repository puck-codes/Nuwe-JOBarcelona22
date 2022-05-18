package tokyo.boblennon.bcnjob22.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.core.ApplicationBase;
import tokyo.boblennon.bcnjob22.core.mappers.UserMapper;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.GetUserDto;
import tokyo.boblennon.bcnjob22.core.mappers.dtos.PostUserDto;
import tokyo.boblennon.bcnjob22.domain.User;
import tokyo.boblennon.bcnjob22.domain.UserReadRepository;
import tokyo.boblennon.bcnjob22.domain.UserWriteRepository;

@Service
@Slf4j
public class UserApplicationImp extends ApplicationBase<User, UUID> implements UserApplication {

    private final UserReadRepository userReadRepository;
    private final UserWriteRepository userWriteRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserApplicationImp(final UserReadRepository userReadRepository,
            final UserWriteRepository userWriteRepository,
            final UserMapper userMapper) {

        super((id) -> userReadRepository.findById(id));

        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
        this.userMapper = userMapper;
    }

    @Override
    public GetUserDto addUser(PostUserDto postUserDto) {

        User user = this.userMapper.toUser(postUserDto);
        user.setId(UUID.randomUUID());

        // Encriptation for password
        // user.setPassword(BCrypt.hashpw(postUserDto.getPassword(), BCrypt.gensalt()));

        // Validation for both Entity and DTO fields and check if User with given 'username' is already registered
        user.validate("username", user.getUsername(), (username) -> this.userReadRepository.exists(username));

        this.userWriteRepository.add(user);
        log.info(this.serializeObject(user, "added"));

        return this.userMapper.toGetUserDto(user);
    }

    @Override
    public GetUserDto updateUser(PostUserDto postUserDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        this.userWriteRepository.addRoleToUser(userName, roleName);
    }

    @Override
    public GetUserDto getUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        return this.userReadRepository.findAll();
    }

}
