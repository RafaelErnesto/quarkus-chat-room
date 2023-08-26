package org.chat.resource.response.mapper;

import jakarta.ws.rs.core.Response;
import org.chat.resource.dto.CreateRoomResponseDto;

import java.util.UUID;

public class CreateRoomResponseMapper {

    public static Response toResponse(UUID id) {
        return Response.status(Response.Status.CREATED).entity(new CreateRoomResponseDto(id.toString())).build();
    }
}
