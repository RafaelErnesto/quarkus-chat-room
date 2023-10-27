package org.chat.application.exception;

import jakarta.ws.rs.ProcessingException;

public class InvalidChatNameException extends ProcessingException {
    public InvalidChatNameException(String message) {
        super(message);
    }
}
