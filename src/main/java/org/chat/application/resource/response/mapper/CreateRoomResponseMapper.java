package org.chat.application.resource.response.mapper;

import jakarta.ws.rs.core.Response;
import org.chat.application.dto.CreateRoomResponseDto;

import java.util.UUID;

public class CreateRoomResponseMapper {

    public static Response toResponse(UUID id) {
        return Response.status(Response.Status.CREATED).entity(new CreateRoomResponseDto(id.toString())).build();
    }
}
