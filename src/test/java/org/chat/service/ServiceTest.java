package org.chat.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.enums.Status;
import org.chat.exception.RoomNotFoundException;
import org.chat.repository.MongoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class ServiceTest {

    @Inject
    Service sut;
    @InjectMock
    MongoRepository repository;

    @Test
    public void Given_an_id_When_room_does_not_exist_Then_throws() {
        Assertions.assertThrows(RoomNotFoundException.class, () -> sut.get(UUID.randomUUID()));
    }

    @Test
    public void Given_an_id_When_room_exists_Then_returns_room() {
        Mockito.when(repository.get(any())).thenReturn(new ChatRoom("Test"));
        ChatRoom roomFound = sut.get(UUID.randomUUID());
        Assertions.assertEquals("Test", roomFound.getName());
        Assertions.assertEquals(Status.PUBLIC, roomFound.getStatus());
    }

    @Test
    public void Given_an_id_When_room_exists_and_is_private_Then_returns_room_status_as_private() {
        Mockito.when(repository.get(any())).thenReturn(new ChatRoom("Test", "123456789"));
        ChatRoom roomFound = sut.get(UUID.randomUUID());
        Assertions.assertEquals("Test", roomFound.getName());
        Assertions.assertEquals(Status.PRIVATE, roomFound.getStatus());
    }

    @Test
    public void Given_a_call_to_create_a_room_When_a_unexpected_database_error_happens_Then_propagate_the_error() {
        Mockito.doThrow(new ProcessingException("Unexpected error")).when(repository).save(any());
        Exception ex = Assertions.assertThrows(ProcessingException.class, () -> sut.create(new CreateChatRoomDto("Test")));
        Assertions.assertEquals("Unexpected error", ex.getMessage());
    }

    @Test
    public void Given_a_call_to_create_a_room_When_it_creates_Then_returns_the_room_id() {
        UUID id = sut.create(new CreateChatRoomDto("Test"));
        Assertions.assertNotNull(id);
    }
}
