package org.chat.infraestructure.repository;

import jakarta.enterprise.context.ApplicationScoped;
import org.chat.domain.ChatRoomRepositoryI;
import org.chat.domain.entity.ChatRoom;

import java.util.UUID;

@ApplicationScoped
public class MongoRepository implements ChatRoomRepositoryI {
    @Override
    public void save(ChatRoom chatRoom) {

    }

    @Override
    public ChatRoom get(UUID id){
        return null;
    }

    @Override
    public void update(ChatRoom roomToUpdate) {

    }

    @Override
    public void delete(UUID id) {

    }
}
