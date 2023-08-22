package org.chat.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.chat.dto.CreateChatRoomDto;
import org.chat.dto.UpdateNameDto;
import org.chat.entity.ChatRoom;
import org.chat.service.Service;

import java.util.UUID;

@Path("/chat-room")
@ApplicationScoped
public class Resource {

    @Inject
    Service service;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatRoom getRoom(@Valid @PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public UUID createRoom(@Valid CreateChatRoomDto createChatRoomDto) {
        return service.create(createChatRoomDto);
    }

    @PATCH
    @Path("/{id}/update-name")
    @Produces(MediaType.APPLICATION_JSON)
    public void updateRoom(@Valid @PathParam("id") UUID id, UpdateNameDto body) {
        service.updateChatName(id, body);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRoom(@Valid @PathParam("id") UUID id) {
        service.delete(id);
    }


}
