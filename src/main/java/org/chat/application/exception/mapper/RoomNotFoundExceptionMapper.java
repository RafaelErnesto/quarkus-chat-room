package org.chat.application.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.chat.application.exception.ErrorResponseDto;
import org.chat.application.exception.RoomNotFoundException;


@Provider
public class RoomNotFoundExceptionMapper implements ExceptionMapper<RoomNotFoundException> {
    @Override
    public Response toResponse(RoomNotFoundException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponseDto).build();
    }
}
