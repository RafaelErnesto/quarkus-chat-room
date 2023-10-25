package org.chat.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.chat.dto.CreateChatRoomDto;
import org.chat.dto.UpdateNameDto;
import org.chat.domain.entity.ChatRoom;
import org.chat.resource.response.mapper.CreateRoomResponseMapper;
import org.chat.service.Service;

import java.util.UUID;

@Path("/chat-room")
@ApplicationScoped
public class Resource {

    @Inject
    Service service;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ChatRoom getRoom(@Valid @PathParam("id") UUID id) {
        return service.get(id);
    }

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRoom(@Valid CreateChatRoomDto createChatRoomDto) {
        UUID id = service.create(createChatRoomDto);
        return CreateRoomResponseMapper.toResponse(id);
    }

    @PATCH
    @Path("/{id}/update-name")
    public void updateRoom(@Valid @PathParam("id") UUID id, @Valid  UpdateNameDto body) {
        service.updateChatName(id, body);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteRoom(@Valid @PathParam("id") UUID id) {
        service.delete(id);
    }

}
