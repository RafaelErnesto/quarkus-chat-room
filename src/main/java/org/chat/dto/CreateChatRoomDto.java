package org.chat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.chat.enums.Status;

public class CreateChatRoomDto {


    @JsonProperty("name")
    @NotBlank(message = "name cannot be blank")
    public String name;
    @JsonProperty("status")
    public Status status = Status.PUBLIC;

    @JsonProperty("password")
    @Size(min = 8, max = 25, message = "password should have between [{min},{max}]")
    public String password;

}
