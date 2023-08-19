package org.chat.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.enums.Status;
import org.chat.repository.ChatRoomRepositoryI;

import java.util.UUID;

@ApplicationScoped
public class Service {

    @Inject
    ChatRoomRepositoryI dynamoDbRepository;

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = chatBuilder(createChatRoomDto);
        dynamoDbRepository.save(chatRoom);
        return chatRoom.id;
    }

    public ChatRoom get(UUID id) throws ProcessingException {
        return dynamoDbRepository.get(id);
    }

    private ChatRoom chatBuilder(CreateChatRoomDto createChatRoomDto) {
        if (createChatRoomDto.status == Status.PRIVATE) {
            return new ChatRoom(createChatRoomDto.name, createChatRoomDto.status, createChatRoomDto.password);
        }
        return new ChatRoom(createChatRoomDto.name);
    }

}
