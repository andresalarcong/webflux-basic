package co.alarcon.fenix.mapper;

import co.alarcon.fenix.model.MessageEntity;
import co.alarcon.fenix.service.dto.CreateMessageDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MessageMapper {

    public MessageEntity mapMessageDTO(CreateMessageDTO createMessageDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createMessageDTO, MessageEntity.class);
    }

    public CreateMessageDTO mapMessageEntity(MessageEntity createMessage) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createMessage, CreateMessageDTO.class);
    }
}
