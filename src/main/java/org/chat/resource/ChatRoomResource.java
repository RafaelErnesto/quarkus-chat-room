package org.chat.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.service.ChatRoomService;

import java.util.UUID;

@Path("/chat-room")
@ApplicationScoped
public class ChatRoomResource {

    @Inject
    ChatRoomService chatRoomService;

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public UUID createChat(@Valid CreateChatRoomDto createChatRoomDto){
        return chatRoomService.create(createChatRoomDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatRoom createChat(@Valid @PathParam("id") UUID id) throws JsonProcessingException {
        return chatRoomService.get(id);
    }
}
