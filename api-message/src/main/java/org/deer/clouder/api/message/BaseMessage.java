package org.deer.clouder.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.ZoneId;

public abstract class BaseMessage {

    @JsonProperty
    private final String createdAt;

    protected BaseMessage(final String createdAt) {
        this.createdAt = createdAt;
    }

    protected BaseMessage() {
        this(LocalDateTime.now().atZone(ZoneId.of("GMT+0")).toString());
    }

    public String createdAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
            "createdAt='" + createdAt + '\'' +
            '}';
    }
}
