package org.chat.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.enums.Status;
import org.chat.repository.ChatRoomRepositoryI;

import java.util.UUID;

@ApplicationScoped
public class ChatRoomService {

    @Inject
    ChatRoomRepositoryI chatRoomRepository;

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = chatBuilder(createChatRoomDto);
        chatRoomRepository.save(chatRoom);
        return chatRoom.id;
    }

    public ChatRoom get(UUID id) throws JsonProcessingException {
        return chatRoomRepository.get(id);
    }

    private ChatRoom chatBuilder(CreateChatRoomDto createChatRoomDto){
        if (createChatRoomDto.status == Status.PRIVATE) {
            return new ChatRoom(createChatRoomDto.name, createChatRoomDto.status, createChatRoomDto.password);
        }
        return new ChatRoom(createChatRoomDto.name);
    }

}
