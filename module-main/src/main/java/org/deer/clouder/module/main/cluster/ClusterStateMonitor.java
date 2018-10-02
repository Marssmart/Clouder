package org.deer.clouder.module.main.cluster;

import org.deer.clouder.api.message.node.NodeLiving;
import org.deer.clouder.api.message.node.NodeReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClusterStateMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterStateMonitor.class);

    @RabbitListener(queues = {"NODE_READY_QUEUE"})
    public void onNodeReady(NodeReady message) {
        LOG.info("Node {} became available at {}", message.nodeName(), message.createdAt());
    }

    @RabbitListener(queues = {"NODE_LIVING_QUEUE"})
    public void onNodeLiving(NodeLiving message) {
        LOG.info("Node {} alive at {}", message.nodeName(), message.createdAt());
    }
}
