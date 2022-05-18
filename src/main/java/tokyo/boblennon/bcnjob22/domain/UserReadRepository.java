package tokyo.boblennon.bcnjob22.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import tokyo.boblennon.bcnjob22.core.ExistsByField;
import tokyo.boblennon.bcnjob22.core.FindById;

public interface UserReadRepository extends FindById<User, UUID>, ExistsByField {
    public Optional<User> findById(UUID id);
    public List<User> findAll();

    public boolean existsByEmail(String email);
}
