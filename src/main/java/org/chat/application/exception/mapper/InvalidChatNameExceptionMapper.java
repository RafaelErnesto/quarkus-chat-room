package org.chat.application.exception.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.chat.application.exception.ErrorResponseDto;
import org.chat.application.exception.InvalidChatNameException;

@Provider
public class InvalidChatNameExceptionMapper implements ExceptionMapper<InvalidChatNameException> {
    @Override
    public Response toResponse(InvalidChatNameException exception) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponseDto).build();
    }
}
