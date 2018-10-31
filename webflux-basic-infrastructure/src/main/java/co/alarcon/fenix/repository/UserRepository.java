package co.alarcon.fenix.repository;

import co.alarcon.fenix.model.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, String> {

}
