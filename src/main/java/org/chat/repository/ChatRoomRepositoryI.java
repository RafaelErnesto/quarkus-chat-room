package org.chat.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.chat.entity.Chat;

import java.util.UUID;

public interface ChatRoomRepositoryI {

    void save(Chat chat);

    Chat get(UUID id) throws JsonProcessingException;

    void delete(UUID id);
}
