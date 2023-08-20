package org.chat.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.exception.RoomNotFoundException;
import org.chat.repository.MongoRepository;

import java.util.UUID;

@ApplicationScoped
public class Service {

    @Inject
    MongoRepository dynamoDbRepository;

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = chatBuilder(createChatRoomDto);
        dynamoDbRepository.save(chatRoom);
        return chatRoom.id;
    }

    public ChatRoom get(UUID id) {
        ChatRoom roomFound = dynamoDbRepository.get(id);
        if(roomFound == null) {
            throw new RoomNotFoundException("Room not found");
        }
        return roomFound;
    }

    private ChatRoom chatBuilder(CreateChatRoomDto createChatRoomDto) {
        if (createChatRoomDto.password == null) {
            return new ChatRoom(createChatRoomDto.name);
        }
        return new ChatRoom(createChatRoomDto.name, createChatRoomDto.password);
    }

}
