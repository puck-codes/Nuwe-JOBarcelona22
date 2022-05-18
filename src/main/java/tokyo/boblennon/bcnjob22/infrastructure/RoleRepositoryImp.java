package tokyo.boblennon.bcnjob22.infrastructure;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.domain.Role;
import tokyo.boblennon.bcnjob22.domain.RoleReadRepository;
import tokyo.boblennon.bcnjob22.domain.RoleWriteRepository;

@Service
@Transactional
@Slf4j
public class RoleRepositoryImp implements RoleReadRepository, RoleWriteRepository {

    private final RoleMongoRepository roleMongoRepository;

    public RoleRepositoryImp(final RoleMongoRepository roleMongoRepository) {
        this.roleMongoRepository = roleMongoRepository;
    }

    @Override
    public Role add(Role role) {
        log.info("Saving role: {}", role);
        return this.roleMongoRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        log.info("Removing role: {}", role);
        this.delete(role);
    }

    @Override
    public boolean exists(String name) {
        log.info("Check if role: '{}'' exists in the database", name);
        return this.roleMongoRepository.exists(name);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        log.info("Fetching role with id '{}'", id);
        return this.roleMongoRepository.findById(id);
    }

}
