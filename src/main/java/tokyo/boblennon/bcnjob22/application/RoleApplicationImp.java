package tokyo.boblennon.bcnjob22.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tokyo.boblennon.bcnjob22.core.ApplicationBase;
import tokyo.boblennon.bcnjob22.domain.Role;
import tokyo.boblennon.bcnjob22.domain.RoleReadRepository;
import tokyo.boblennon.bcnjob22.domain.RoleWriteRepository;

@Service
@Slf4j
public class RoleApplicationImp extends ApplicationBase<Role, UUID> implements RoleApplication {

    private final RoleReadRepository roleReadRepository;
    private final RoleWriteRepository roleWriteRepository;

    public RoleApplicationImp(final RoleReadRepository roleReadRepository,
            final RoleWriteRepository roleWriteRepository) {
        super((id) -> roleReadRepository.findById(id));

        this.roleReadRepository = roleReadRepository;
        this.roleWriteRepository = roleWriteRepository;
    }

    @Override
    public void addRole(Role role) {
        role.setId(UUID.randomUUID());

        role.validate("name", role.getName(), (name) -> this.roleReadRepository.exists(name));

        this.roleWriteRepository.add(role);
        log.info(this.serializeObject(role, "added"));
    }
}
