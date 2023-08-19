package org.chat.entity;

import org.chat.enums.Status;
import org.chat.valueObjects.Password;

import java.util.UUID;

public class ChatRoom {
    public final UUID id = UUID.randomUUID();

    public String name;

    public Status status;

    public Password password;

    public ChatRoom(String name, Status status, String pwd){
        this.name = name;
        this.status = status;
        this.password = new Password(pwd);
    }

    public ChatRoom(String name){
        this.name = name;
        this.status = Status.PUBLIC;
    }
}
