package com.example.licencjat.conversations.ConversationsCRUD;

import com.example.licencjat.UI.idGenerator.IdGenerator;
import com.example.licencjat.conversations.ConversationsCRUD.models.Conversation;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationListDTO;
import com.example.licencjat.conversations.ConversationsCRUD.models.ConversationWebInput;
import com.example.licencjat.conversations.ConversationsUser.ConversationUserRepository;
import com.example.licencjat.conversations.ConversationsUser.ConversationsUser;
import com.example.licencjat.exceptions.NotFoundExceptions.IncorrectIdInputException;
import com.example.licencjat.messages.MessageRepository;
import com.example.licencjat.messages.models.MessageOutPut;
import com.example.licencjat.userData.UserRepository;
import com.example.licencjat.userData.models.UserChatDto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConversationsServiceImpl implements ConversationsService{
    private final ConversationRepository conversationRepository;
    private final ConversationUserRepository conversationsUserRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final IdGenerator idGenerator;

    @Override

    public List<ConversationListDTO> getConversations() {
        var convs = conversationRepository.findAll().stream()
                .map(c -> {
                    var conv = mapper.map(c, ConversationListDTO.class);
                    conv.setMessages(messageRepository.getMessageByConversationId(c.getId()).stream().map(m -> MessageOutPut.builder()
                            .senderId(m.getOwnerId())
                            .text(m.getContent())
                            .conversationId(c.getId())
                            .build()).collect(Collectors.toSet()));

                    for (var m :
                            c.getMessages()) {
                        System.out.println(m.getContent());
                    }
                    return conv;
                })
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
    public ConversationListDTO createConversation(ConversationWebInput webInput) {
        var id = UUID.fromString(idGenerator.generateId());

        var conv = Conversation.builder()
                .id(id)
                .name(webInput.getName()).build();

        System.out.println("elo");

        conversationRepository.save(conv);

        var usersDto = new HashSet<UserChatDto>();

        for (var userId :
                webInput.getUsers()) {
            var user = userRepository.findById(userId).orElseThrow(IncorrectIdInputException::new);
            conversationsUserRepository.save(ConversationsUser.builder()
                    .id(UUID.fromString(idGenerator.generateId()))
                    .conversation(conv)
                    .user(user).build());
            usersDto.add(mapper.map(user, UserChatDto.class));
        }

        var convDTO = new ConversationListDTO();

        convDTO.setConversationName(webInput.getName());
        convDTO.setUsers(usersDto);
        convDTO.setMessages(new HashSet<>());
        convDTO.setConversationId(id);

        return convDTO;
    }
}
