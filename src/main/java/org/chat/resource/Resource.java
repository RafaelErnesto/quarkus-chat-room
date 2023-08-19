package org.chat.resource;

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
import org.chat.service.Service;

import java.util.UUID;

@Path("/chat-room")
@ApplicationScoped
public class Resource {

    @Inject
    Service service;

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public UUID createRoom(@Valid CreateChatRoomDto createChatRoomDto){
        return service.create(createChatRoomDto);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatRoom getRoom(@Valid @PathParam("id") UUID id) {
        return service.get(id);
    }
}
