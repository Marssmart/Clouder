package org.deer.clouder.api.message.queue;

import org.deer.clouder.api.message.topic.SystemQueueTopic;

public enum Queues {

    SYSTEM_QUEUE(SystemQueueTopic.class);

    private final Class<? extends Enum<?>> allowedTopics;

    Queues(Class<? extends Enum<?>> allowedTopics) {
        this.allowedTopics = allowedTopics;
    }

    public Class<? extends Enum<?>> allowedTopics() {
        return allowedTopics;
    }
}
