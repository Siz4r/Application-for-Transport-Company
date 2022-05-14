package com.example.licencjat.conversations.ConversationsCRUD;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversationsServiceImpl implements ConversationsService{
    private final ConversationRepository conversationRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;

    @Override
    public List<ConversationListDTO> getConversations() {
        return conversationRepository.findAll().stream().map(c -> mapper.map(c, ConversationListDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteConversation(UUID id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public UUID createConversation(ConversationWebInput webInput) {
        var id = UUID.fromString(idGenerator.generateId());

        var conv = Conversation.builder()
                .id(id)
                .name(webInput.getName()).build();

        conversationRepository.save(conv);

        return id;
    }
}
