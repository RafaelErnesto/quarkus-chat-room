package org.chat.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import org.chat.dto.CreateChatRoomDto;
import org.chat.service.ChatRoomService;

import java.util.UUID;

@Path("/chat")
@ApplicationScoped
public class ChatResource {

    @Inject
    ChatRoomService chatRoomService;


    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public UUID createChat(@Valid CreateChatRoomDto createChatRoomDto){
        return chatRoomService.create(createChatRoomDto);
    }
}
