package org.chat.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class UpdateNameDto {
    @JsonProperty("name")
    @NotBlank(message = "name cannot be blank")
    private final String name;

    public UpdateNameDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
