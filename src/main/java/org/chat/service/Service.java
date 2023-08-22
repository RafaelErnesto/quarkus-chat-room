package org.chat.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.dto.CreateChatRoomDto;
import org.chat.dto.UpdateNameDto;
import org.chat.entity.ChatRoom;
import org.chat.exception.RoomNotFoundException;
import org.chat.repository.MongoRepository;

import java.util.UUID;

@ApplicationScoped
public class Service {

    @Inject
    MongoRepository repository;

    public ChatRoom get(UUID id) {
        ChatRoom roomFound = repository.get(id);
        if (roomFound == null) {
            throw new RoomNotFoundException("Room not found");
        }
        return roomFound;
    }

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = chatBuilder(createChatRoomDto);
        repository.save(chatRoom);
        return chatRoom.id;
    }

    public void updateChatName(UUID id, UpdateNameDto updateNameDto) {
        ChatRoom roomToUpdate = repository.get(id);
        if (roomToUpdate == null) {
            throw new RoomNotFoundException("Room not found");
        }
        roomToUpdate.setName(updateNameDto.getName());
        repository.update(roomToUpdate);
    }

    public void delete(UUID id) {
        ChatRoom roomToUpdate = repository.get(id);
        if (roomToUpdate == null) {
            throw new RoomNotFoundException("Room not found");
        }
        repository.delete(id);
    }


    private ChatRoom chatBuilder(CreateChatRoomDto createChatRoomDto) {
        if (createChatRoomDto.password == null) {
            return new ChatRoom(createChatRoomDto.name);
        }
        return new ChatRoom(createChatRoomDto.name, createChatRoomDto.password);
    }

}
