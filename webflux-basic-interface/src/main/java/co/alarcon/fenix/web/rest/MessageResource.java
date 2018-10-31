package co.alarcon.fenix.web.rest;

import javax.validation.Valid;

import co.alarcon.fenix.service.MessageService;
import co.alarcon.fenix.service.dto.CreateMessageDTO;
import co.alarcon.fenix.service.dto.UpdateMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.alarcon.fenix.model.MessageEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api")
public class MessageResource {

    private final Logger log = LoggerFactory.getLogger(MessageResource.class);

    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public Flux<MessageEntity> getAllMessages() {
        return messageService.getAllMessages();
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public Mono<MessageEntity> createMessage(@Valid @RequestBody CreateMessageDTO messageDto) {
        return messageService.createMessage(messageDto);
    }

    @RequestMapping(value = "/messages/{id}", method = RequestMethod.GET)
    public Mono<ResponseEntity<MessageEntity>> getMessagesById(@PathVariable(value = "id") String messageId) {
        return messageService.getMessagesById(messageId);
    }
    
    @RequestMapping(value = "/messages/{id}", method = RequestMethod.PUT)
    public Mono<ResponseEntity<MessageEntity>> updateMessage(@PathVariable(value = "id") String messageId,
                                                             @Valid @RequestBody UpdateMessageDTO messageDto) {
        return messageService.updateMessage(messageId, messageDto)
                .map(updateMessage -> new ResponseEntity<>(updateMessage, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @RequestMapping(value = "/messages/{id}", method = RequestMethod.DELETE)
    public Mono<ResponseEntity<Void>> deleteMessage(@PathVariable(value = "id") String messageId) {
        return messageService.deleteMessage(messageId)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
