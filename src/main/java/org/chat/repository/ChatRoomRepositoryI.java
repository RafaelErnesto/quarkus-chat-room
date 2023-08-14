package org.chat.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.chat.entity.ChatRoom;

import java.util.UUID;

public interface ChatRoomRepositoryI {

    void save(ChatRoom chatRoom);

    ChatRoom get(UUID id) throws JsonProcessingException;

    void delete(UUID id);
}
