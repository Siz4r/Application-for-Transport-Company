package com.example.licencjat.messages;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.ConversationRepository;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.messages.models.Message;
import com.example.licencjat.messages.models.MessageInput;
import com.example.licencjat.messages.models.MessageOutPut;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final ConversationRepository conversationRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper mapper;

    public MessageOutPut addMessage(MessageInput input) {
        var id = idGenerator.generateId();
        var conversation = conversationRepository.findById(input.getConversationId()).orElseThrow(IncorrectIdInputException::new);

        return mapper.map(repository.save(Message.builder()
                .id(UUID.fromString(id))
                .conversation(conversation)
                .content(input.getContent())
                .ownerId(input.getOwnerId())
                .createdAt(new Timestamp(System.currentTimeMillis())).build()), MessageOutPut.class);
    }

}
