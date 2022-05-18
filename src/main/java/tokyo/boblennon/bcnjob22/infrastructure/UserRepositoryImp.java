package tokyo.boblennon.bcnjob22.infrastructure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.domain.Role;
import tokyo.boblennon.bcnjob22.domain.User;
import tokyo.boblennon.bcnjob22.domain.UserReadRepository;
import tokyo.boblennon.bcnjob22.domain.UserWriteRepository;

@Service
@Transactional
@Slf4j
public class UserRepositoryImp implements UserReadRepository, UserWriteRepository, UserDetailsService {

    private final UserMongoRepository userMongoRepository;
    private final RoleMongoRepository roleMongoRepository;

    @Autowired
    public UserRepositoryImp(final UserMongoRepository userMongoRepository,
            final RoleMongoRepository roleMongoRepository) {
        this.userMongoRepository = userMongoRepository;
        this.roleMongoRepository = roleMongoRepository;
    }

    @Override
    public User add(User user) {
        log.info("Saving user: {}", user);
        return this.userMongoRepository.save(user);
    }

    @Override
    public User update(User user) {
        log.info("Updating user: {}", user);
        return this.userMongoRepository.save(user);
    }

    @Override
    public void delete(User user) {
        log.info("Removing user: {}", user);
        this.userMongoRepository.delete(user);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role '{}' to user '{}'", userName, roleName);
        User user = this.userMongoRepository.findByUsername(userName);
        Role role = this.roleMongoRepository.findByName(roleName);
        user.getRoles().add(role);
        this.update(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        log.info("Fetching user with id '{}'", id);
        return this.userMongoRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        log.info("Fetching all users");
        return this.userMongoRepository.findAll();
    }

    @Override
    public boolean exists(String username) {
        log.info("Check if User with username: '{}' exists in the database", username);
        return this.userMongoRepository.exists(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        log.info("Check if User with email: '{}' exists in the database", email);
        return this.userMongoRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userMongoRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        log.info("User was found in the database: {}", user);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

}
