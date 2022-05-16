package com.example.licencjat.conversations.ConversationsCRUD;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;
import com.example.licencjat.conversations.ConversationsUser.ConversationUserRepository;
import com.example.licencjat.conversations.ConversationsUser.ConversationsUser;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.userData.UserRepository;
import com.example.licencjat.userData.models.UserChatDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversationsServiceImpl implements ConversationsService{
    private final ConversationRepository conversationRepository;
    private final ConversationUserRepository conversationsUserRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;

    @Override

    public List<ConversationListDTO> getConversations() {
        var convs = conversationRepository.findAll().stream()
                .map(c -> mapper.map(c, ConversationListDTO.class))
                .collect(Collectors.toList());

        for (var c :
                convs) {
            var conversationUsers = conversationsUserRepository.getAllByConversation_Id(c.getConversationId());
            c.setUsers(new HashSet<>());
            for (var convUser :
                    conversationUsers) {
                c.getUsers().add(mapper.map(convUser.getUser(), UserChatDto.class));
            }
        }

        return convs;
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

        for (var userId :
                webInput.getUsers()) {
            var user = userRepository.findById(userId).orElseThrow(IncorrectIdInputException::new);
            conversationsUserRepository.save(ConversationsUser.builder()
                    .id(UUID.fromString(idGenerator.generateId()))
                    .conversation(conv)
                    .user(user).build());
        }


        return id;
    }
}
