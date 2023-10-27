package org.chat.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.domain.ChatRoomRepositoryI;
import org.chat.domain.builder.ChatRoomBuilder;
import org.chat.application.dto.CreateChatRoomDto;
import org.chat.application.dto.UpdateNameDto;
import org.chat.domain.entity.ChatRoom;
import org.chat.application.exception.RoomNotFoundException;

import java.util.UUID;

@ApplicationScoped
public class Service {

    @Inject
    ChatRoomRepositoryI repository;

    ChatRoomBuilder builder = new ChatRoomBuilder();

    public ChatRoom get(UUID id) {
        return findChatRoom(id);
    }

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        ChatRoom chatRoom = builder
                .withName(createChatRoomDto.name)
                .withPassword(createChatRoomDto.password).build();

        repository.save(chatRoom);
        return chatRoom.id;
    }

    public UUID updateChatName(UUID id, UpdateNameDto updateNameDto) {
        ChatRoom roomToUpdate = findChatRoom(id);
        roomToUpdate.setName(updateNameDto.getName());
        repository.update(roomToUpdate);
        return roomToUpdate.id;
    }

    public void delete(UUID id) {
        findChatRoom(id);
        repository.delete(id);
    }

    private ChatRoom findChatRoom(UUID id) {
        ChatRoom chatRoom = repository.get(id);
        if (chatRoom == null) {
            throw new RoomNotFoundException("Room not found");
        }
        return chatRoom;
    }

}
