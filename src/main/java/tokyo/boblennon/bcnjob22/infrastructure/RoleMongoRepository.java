package tokyo.boblennon.bcnjob22.infrastructure;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tokyo.boblennon.bcnjob22.domain.Role;

@Repository
public interface RoleMongoRepository extends MongoRepository<Role, UUID> {

    @ExistsQuery("{ 'name': ?0}")
    boolean exists(String name);

    Role findByName(String name);
}
