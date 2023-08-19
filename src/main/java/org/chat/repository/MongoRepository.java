package org.chat.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.chat.entity.ChatRoom;

import java.util.UUID;

@ApplicationScoped
public class MongoRepository implements ChatRoomRepositoryI{
    @Override
    public void save(ChatRoom chatRoom) {

    }

    @Override
    public ChatRoom get(UUID id){
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
