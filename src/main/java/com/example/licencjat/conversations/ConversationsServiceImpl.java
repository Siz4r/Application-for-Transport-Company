package com.example.licencjat.conversations;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.models.Conversation;
import com.example.licencjat.conversations.models.ConversationListDTO;
import com.example.licencjat.conversations.models.ConversationWebInput;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.security.AuthenticationFacade;
import com.example.licencjat.user.UserRepository;
import com.example.licencjat.user.models.UserChatDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversationsServiceImpl implements ConversationsService{
    private final ConversationRepository conversationRepository;
    private final AuthenticationFacade authenticationFacade;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;

    @Override

    public List<ConversationListDTO> getConversations() {
        var userId = authenticationFacade.getCurrentAuthenticatedUser().getId();

        return conversationRepository.findAll().stream()
                .filter(conversation -> conversation.getUsers().stream().filter(user -> userId.equals(user.getId())).count() == 1)
                .map(conversation -> mapper.map(conversation, ConversationListDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteConversation(UUID id) {
        conversationRepository.deleteById(id);
    }

    @Override
    public ConversationListDTO createConversation(ConversationWebInput webInput) {
        var id = UUID.fromString(idGenerator.generateId());

        var conv = Conversation.builder()
                .id(id)
                .name(webInput.getName())
                .users(new HashSet<>())
                .build();

        for (var userId :
                webInput.getUsers()) {
            var user = userRepository.findById(userId).orElseThrow(IncorrectIdInputException::new);
            user.addConv(conv);
        }

        conversationRepository.save(conv);

        return mapper.map(conv, ConversationListDTO.class);
    }
}
