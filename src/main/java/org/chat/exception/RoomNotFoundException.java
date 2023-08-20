package org.chat.exception;

import jakarta.ws.rs.ProcessingException;

public class RoomNotFoundException extends ProcessingException {

    public RoomNotFoundException(String message) {
        super(message);
    }
}
