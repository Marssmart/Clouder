package org.deer.clouder.module.main.cluster;

import org.deer.clouder.api.message.node.NodeReady;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClusterStateMonitor {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterStateMonitor.class);

    public void onNodeReady(NodeReady message) {
        LOG.info("Node {} became available at {}", message.nodeName(), message.createdAt());
    }
}
