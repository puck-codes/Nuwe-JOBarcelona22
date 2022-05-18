package tokyo.boblennon.bcnjob22.infrastructure;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tokyo.boblennon.bcnjob22.domain.User;

@Repository
public interface UserMongoRepository extends MongoRepository<User, UUID> {

    @ExistsQuery("{ 'username': ?0}")
    boolean exists(String username);

    User findByUsername(String username);
}
