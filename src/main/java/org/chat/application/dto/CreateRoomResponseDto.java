package org.chat.application.dto;

public class CreateRoomResponseDto {

    private final String id;

    public CreateRoomResponseDto(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
