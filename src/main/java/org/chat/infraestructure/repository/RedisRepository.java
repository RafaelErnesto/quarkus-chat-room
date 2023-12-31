package org.chat.infraestructure.repository;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.domain.ChatRoomRepositoryI;
import org.chat.domain.entity.ChatRoom;

import java.util.UUID;

@ApplicationScoped
public class RedisRepository implements ChatRoomRepositoryI {

    @Inject
    RedisDataSource redisDataSource;


    @Override
    public void save(ChatRoom chatRoom) {
        ValueCommands<String, ChatRoom> commands = redisDataSource.value(ChatRoom.class);
        commands.set(chatRoom.id.toString(), chatRoom);
    }

    @Override
    public ChatRoom get(UUID id)  {
       ValueCommands<String, ChatRoom> commands = redisDataSource.value(ChatRoom.class);
       return commands.get(id.toString());
    }

    @Override
    public void update(ChatRoom roomToUpdate) {

    }

    @Override
    public void delete(UUID id) {
        ValueCommands<String, ChatRoom> commands = redisDataSource.value(ChatRoom.class);
        commands.getdel(id.toString());
    }
}
