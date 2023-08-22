package org.chat.entity;

import org.chat.enums.Status;
import org.chat.valueObjects.Name;
import org.chat.valueObjects.Password;

import java.util.UUID;

public class ChatRoom {
    public final UUID id = UUID.randomUUID();

    private Name name;

    private Status status;

    private Password password;

    public ChatRoom(String name, String pwd){
        setName(name);
        setStatus(Status.PRIVATE);
        setPassword(pwd);
    }

    public ChatRoom(String name){
        setName(name);
        setStatus(Status.PUBLIC);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public void setName(String name) {
        this.name = new Name(name);
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
