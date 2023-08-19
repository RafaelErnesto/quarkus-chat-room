package org.chat.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.chat.dto.CreateChatRoomDto;
import org.chat.entity.ChatRoom;
import org.chat.repository.ChatRoomRepositoryI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.shaded.org.zeroturnaround.exec.ProcessInitException;

import java.util.UUID;

@QuarkusTest
public class ServiceTest {

    @Inject
    Service sut;
    @InjectMock
    ChatRoomRepositoryI repository;

    @Test
    public void getRoomNotFound() {

        Assertions.assertThrows(ProcessInitException.class, () -> sut.get(UUID.randomUUID()));
    }

    @Test
    public void getRoom() {

        ChatRoom result = sut.get(UUID.randomUUID());
    }

    @Test
    public void createRoomDatabaseError() {

        Assertions.assertThrows(ProcessInitException.class, () -> sut.create(new CreateChatRoomDto()));
    }

    @Test
    public void createRoom() {

        UUID result = sut.create(new CreateChatRoomDto());
    }
}
