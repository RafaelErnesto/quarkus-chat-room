package org.chat.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.Chat;
import org.chat.enums.Status;
import org.chat.repository.ChatRoomRepositoryI;

import java.util.UUID;

@ApplicationScoped
public class ChatRoomService {

    @Inject
    ChatRoomRepositoryI chatRoomRepository;

    public UUID create(CreateChatRoomDto createChatRoomDto) {
        Chat chat = chatBuilder(createChatRoomDto);
        chatRoomRepository.save(chat);
        return chat.id;
    }

    private Chat chatBuilder(CreateChatRoomDto createChatRoomDto){
        if (createChatRoomDto.status == Status.PRIVATE) {
            return new Chat(createChatRoomDto.name, createChatRoomDto.status, createChatRoomDto.password);
        }
        return new Chat(createChatRoomDto.name);
    }

}
