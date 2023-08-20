package org.chat.entity;

import org.chat.enums.Status;
import org.chat.valueObjects.Password;

import java.util.UUID;

public class ChatRoom {
    public final UUID id = UUID.randomUUID();

    private String name;

    private Status status;

    private Password password;

    public ChatRoom(String name, String pwd){
        this.name = name;
        this.status = Status.PRIVATE;
        this.password = new Password(pwd);
    }

    public ChatRoom(String name){
        this.name = name;
        this.status = Status.PUBLIC;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPassword(String newPassword) {
        this.password = new Password(newPassword);
    }

    public Boolean doesPasswordMatch(String valueToMatch) {
        return this.password.match(valueToMatch);
    }
}
