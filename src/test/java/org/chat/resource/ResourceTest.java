package org.chat.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;
import org.chat.application.resource.Resource;
import org.chat.application.dto.CreateChatRoomDto;
import org.chat.application.dto.UpdateNameDto;
import org.chat.domain.entity.ChatRoom;
import org.chat.application.exception.InvalidChatNameException;
import org.chat.application.exception.RoomNotFoundException;
import org.chat.domain.service.Service;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
@TestHTTPEndpoint(Resource.class)
public class ResourceTest {

    @InjectMock
    Service service;

    @Test
    public void Given_a_request_to_get_a_chat_room_When_chat_room_is_not_found_Then_returns_404() {
        UUID id = UUID.randomUUID();

        Mockito.when(service.get(id)).thenThrow(RoomNotFoundException.class);

        when()
                .get("/" + id)
                .then()
                .statusCode(404);
    }

    @Test
    public void Given_a_request_to_get_a_chat_room_When_chat_room_is_found_Then_returns_chat_room() {
        ChatRoom mockedResult = new ChatRoom("TEST");
        Mockito.when(service.get(mockedResult.id)).thenReturn(mockedResult);

        when()
                .get("/" + mockedResult.id)
                .then()
                .statusCode(200)
                .body("id", equalTo(mockedResult.id.toString()))
                .body("name", equalTo(mockedResult.getName()))
                .body("status", equalTo(mockedResult.getStatus().toString()));
    }

    @Test
    public void Given_a_request_to_create_a_chat_room_When_name_is_invalid_Then_returns_status_code_400() {
        CreateChatRoomDto dto = new CreateChatRoomDto("");
        Mockito.when(service.create(dto)).thenThrow(InvalidChatNameException.class);

        with().body(dto)
                .when()
                .header(new Header("content-type", "application/json"))
                .post()
                .then()
                .statusCode(400);

    }

    @Test
    public void Given_a_request_to_create_a_chat_room_When_creates_Then_returns_id() {
        CreateChatRoomDto dto = new CreateChatRoomDto("test");
        Mockito.when(service.create(Mockito.any())).thenReturn(UUID.randomUUID());

        with().body(dto)
                .when()
                .header(new Header("content-type", "application/json"))
                .post()
                .then()
                .statusCode(201)
                .body("id", notNullValue(String.class));

    }

    @Test
    public void Given_a_request_to_update_the_name_of_chat_room_When_room_is_not_found_Then_returns_status_404() {
        UpdateNameDto updateNameDto = new UpdateNameDto("test");
        Mockito.when(service.updateChatName(Mockito.any(), Mockito.any())).thenThrow(RoomNotFoundException.class);


        with().body(updateNameDto)
                .when()
                .patch("/" + UUID.randomUUID() + "/update-name")
                .then()
                .statusCode(404);

    }

    @Test
    public void Given_a_request_to_update_the_name_of_chat_room_When_room_is_updated_Then_returns_status_202() {
        UpdateNameDto updateNameDto = new UpdateNameDto("test");

        with().body(updateNameDto)
                .when()
                .patch("/" + UUID.randomUUID() + "/update-name")
                .then()
                .statusCode(204);

    }

    @Test
    public void Given_a_request_to_delete_a_chat_room_When_room_is_not_found_Then_returns_status_404() {
        Mockito.doThrow(RoomNotFoundException.class).when(service).delete(Mockito.any());

        when()
                .delete("/" + UUID.randomUUID())
                .then()
                .statusCode(404);

    }

    @Test
    public void Given_a_request_to_delete_a_chat_room_When_room_is_found_Then_returns_status_204() {

        when()
                .delete("/" + UUID.randomUUID())
                .then()
                .statusCode(204);

    }
}
