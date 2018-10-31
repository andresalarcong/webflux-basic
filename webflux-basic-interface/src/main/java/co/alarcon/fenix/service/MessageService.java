package co.alarcon.fenix.service;

import co.alarcon.fenix.mapper.MessageMapper;
import co.alarcon.fenix.model.MessageEntity;
import co.alarcon.fenix.repository.MessageRepository;
import co.alarcon.fenix.service.dto.CreateMessageDTO;
import co.alarcon.fenix.service.dto.UpdateMessageDTO;
import co.alarcon.fenix.web.rest.MessageResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {
	private final Logger log = LoggerFactory.getLogger(MessageResource.class);

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private MessageMapper messageMapper;

	public Mono<MessageEntity> createMessage(CreateMessageDTO createMessageDTO) {
		return messageRepository.save(messageMapper.mapMessageDTO(createMessageDTO));
	}
	
	public Flux<MessageEntity> getAllMessages() {
		return messageRepository.findAll();
	}

	public Mono<MessageEntity> updateMessage(String messageId, UpdateMessageDTO messageDto) {
		return messageRepository.findById(messageId).flatMap(existingMessage -> {
			existingMessage.setEditedAt(messageDto.getEditedAt());
			existingMessage.setText(messageDto.getText());
			return messageRepository.save(existingMessage);
		});
	}

	public Mono<ResponseEntity<MessageEntity>> getMessagesById(String messageId) {
		return messageRepository.findById(messageId).map(savedMessage -> ResponseEntity.ok(savedMessage))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	public Mono<Void> deleteMessage(String messageId) {
		return messageRepository.findById(messageId)
				.flatMap(existingMessage -> messageRepository.delete(existingMessage));
	}

}
