package com.example.licencjat.messages;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.ConversationsCRUD.ConversationRepository;
import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.messages.models.Message;
import com.example.licencjat.messages.models.MessageInput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final ConversationRepository conversationRepository;
    private final IdGenerator idGenerator;

    public void addMessage(MessageInput input) {
        var id = idGenerator.generateId();
        var conversation = conversationRepository.findById(input.getConvID()).orElseThrow(IncorrectIdInputException::new);

        repository.save(new Message(UUID.fromString(id), conversation, input.getSenderID(), input.getContent()));
    }

}
