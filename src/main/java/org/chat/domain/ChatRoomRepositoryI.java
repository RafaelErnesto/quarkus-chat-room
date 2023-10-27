package org.chat.domain;

import org.chat.domain.entity.ChatRoom;

import java.util.UUID;

public interface ChatRoomRepositoryI {

    void save(ChatRoom chatRoom);

    ChatRoom get(UUID id);

    void update(ChatRoom roomToUpdate);

    void delete(UUID id);
}
