package tokyo.boblennon.bcnjob22.application;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.core.ApplicationBase;
import tokyo.boblennon.bcnjob22.core.execptions.BadRequestException;
import tokyo.boblennon.bcnjob22.core.mappers.UserMapper;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserApplicationImp(final UserReadRepository userReadRepository,
            final UserWriteRepository userWriteRepository,
            final UserMapper userMapper, final PasswordEncoder passwordEncoder) {

        super((id) -> userReadRepository.findById(id));

        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User addUser(PostUserDto postUserDto) {

        User user = this.userMapper.toUser(postUserDto);
        user.setId(UUID.randomUUID());

        // Encriptation for password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Validation for both Entity and DTO fields and check if User with given 'username' is already registered
        user.validate("username", user.getUsername(), (username) -> this.userReadRepository.exists(username));

        if (this.userReadRepository.existsByEmail(user.getEmail())) {
            BadRequestException badRequestException = new BadRequestException();
            badRequestException.addException("email",
                    String.format("Value %s for key %s is already in the database.", user.getEmail(), "email"));
            throw badRequestException;
        }

        this.userWriteRepository.add(user);
        log.info(this.serializeObject(user, "added"));
        return user;
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        this.userWriteRepository.addRoleToUser(userName, roleName);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = this.userReadRepository.findAll();
        for (int i = 0; i < users.size(); ++i) {
            if (users.get(i).getUsername().contains("_admin")) {
                users.remove(i);
            }
        }

        return users;
    }
}