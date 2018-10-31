package co.alarcon.fenix.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import co.alarcon.fenix.model.MessageEntity;

@Repository
public interface MessageRepository extends ReactiveMongoRepository<MessageEntity, String>{
	
}
