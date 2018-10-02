package org.deer.clouder.module.events;

import static org.deer.clouder.api.message.queue.Queue.NODE_LIVING_QUEUE;
import static org.deer.clouder.api.message.queue.Queue.NODE_READY_QUEUE;
import static org.deer.clouder.api.message.topic.Topic.NODE_LIFECYCLE_TOPIC;

import org.deer.clouder.api.message.node.NodeLiving;
import org.deer.clouder.api.message.node.NodeReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemEvents {

    private static final Logger LOG = LoggerFactory.getLogger(SystemEvents.class);

    @Autowired
    private RabbitTemplate rabbit;

    @EventListener(ApplicationReadyEvent.class)
    public void whenApplicationReady() {
        LOG.info("Events module ready, sending notification...");
        rabbit.convertAndSend(NODE_LIFECYCLE_TOPIC.name(), NODE_READY_QUEUE.route(),
            new NodeReady("module-events"));
    }

    @Scheduled(fixedRate = 60000)
    public void whenApplicationLiving() {
        LOG.trace("Events module living, sending notification...");
        rabbit.convertAndSend(NODE_LIFECYCLE_TOPIC.name(), NODE_LIVING_QUEUE.route(),
            new NodeLiving("module-events"));
    }
}
