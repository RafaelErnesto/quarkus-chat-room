package org.chat.builder;

import org.chat.domain.entity.ChatRoom;

public class ChatRoomBuilder {
    private String name;

    private String password;

    public ChatRoomBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public ChatRoomBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ChatRoom build() {
        if (this.password != null) {
            return new ChatRoom(this.name, this.password);
        }

        return new ChatRoom(this.name);
    }
}
