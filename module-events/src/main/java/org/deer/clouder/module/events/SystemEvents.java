package org.deer.clouder.module.events;

import static org.deer.clouder.api.message.queue.Queues.SYSTEM_QUEUE;

import org.deer.clouder.api.message.node.NodeReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SystemEvents {

    private static final Logger LOG = LoggerFactory.getLogger(SystemEvents.class);

    @Autowired
    private RabbitTemplate rabbit;

    @EventListener(ApplicationReadyEvent.class)
    public void whenApplicationReady() {
        LOG.info("Events module ready, sending notification...");
        rabbit.convertAndSend(SYSTEM_QUEUE.name(), new NodeReady("module-events"));
    }
}
