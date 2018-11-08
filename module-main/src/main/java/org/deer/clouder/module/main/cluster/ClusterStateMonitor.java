package org.deer.clouder.module.main.cluster;

import java.util.stream.Collectors;
import org.deer.clouder.api.message.node.NodeInfo;
import org.deer.clouder.api.message.node.NodeLiving;
import org.deer.clouder.api.message.node.NodeReady;
import org.deer.clouder.module.main.dto.Node;
import org.deer.clouder.module.main.dto.Prop;
import org.deer.clouder.module.main.repository.NodeRepository;
import org.deer.clouder.module.main.repository.PropRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClusterStateMonitor {

  private static final Logger LOG = LoggerFactory.getLogger(ClusterStateMonitor.class);

  @Autowired
  private NodeRepository nodeRepository;

  @Autowired
  private PropRepository propRepository;

  @RabbitListener(queues = {"NODE_READY_QUEUE"})
  public void onNodeReady(NodeReady message) {
    LOG.info("Node {} became available at {}", message.nodeName(), message.createdAt());
    nodeRepository.save(
        nodeRepository.findById(message.nodeName()).orElse(new Node(message.nodeName()))
            .setLastUpdate(message.createdAt()));
  }

  @RabbitListener(queues = {"NODE_LIVING_QUEUE"})
  public void onNodeLiving(NodeLiving message) {
    LOG.info("Node {} alive at {}", message.nodeName(), message.createdAt());
    nodeRepository.save(
        nodeRepository.findById(message.nodeName()).orElse(new Node(message.nodeName()))
            .setLastUpdate(message.createdAt()));
  }

  @RabbitListener(queues = {"NODE_INFO_QUEUE"})
  public void onNodeInfo(NodeInfo message) {
    LOG.info("Node info received from {} at {}", message.nodeName(), message.createdAt());
    nodeRepository.save(
        nodeRepository.findById(message.nodeName()).orElse(new Node(message.nodeName()))
            .setLastUpdate(message.createdAt())
            .setProperties(message.systemProperties().stream()
                .map(systemProperty -> new Prop(systemProperty.key(), systemProperty.value()))
                .collect(Collectors.toList())));
  }
}
