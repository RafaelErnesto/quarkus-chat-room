package org.chat.application.exception;

import jakarta.ws.rs.ProcessingException;

public class RoomNotFoundException extends ProcessingException {

    public RoomNotFoundException(String message) {
        super(message);
    }

    public RoomNotFoundException() {
        super("Room not found");
    }
}
