package org.chat.exception;

public class ErrorResponseDto {

    String message;

    public ErrorResponseDto(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
