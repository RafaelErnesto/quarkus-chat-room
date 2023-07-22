package org.chat.repository;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.chat.entity.Chat;
import java.util.UUID;

@ApplicationScoped
public class RedisChatRoomRepository implements  ChatRoomRepositoryI{

    @Inject
    RedisDataSource redisDataSource;


    @Override
    public void save(Chat chat) {
        ValueCommands<String, Chat> commands = redisDataSource.value(Chat.class);
        commands.set(chat.id.toString(), chat);
    }

    @Override
    public Chat get(UUID id)  {
       ValueCommands<String, Chat> commands = redisDataSource.value(Chat.class);
       return commands.get(id.toString());
    }

    @Override
    public void delete(UUID id) {
        ValueCommands<String, Chat> commands = redisDataSource.value(Chat.class);
        commands.getdel(id.toString());
    }
}
