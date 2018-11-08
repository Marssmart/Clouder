package org.deer.clouder.module.main.cluster;

import static org.deer.clouder.api.message.queue.Queue.NODE_INFO_QUEUE;
import static org.deer.clouder.api.message.queue.Queue.NODE_LIVING_QUEUE;
import static org.deer.clouder.api.message.queue.Queue.NODE_READY_QUEUE;
import static org.deer.clouder.api.message.topic.Topic.NODE_LIFECYCLE_TOPIC;

import java.util.Arrays;
import java.util.List;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClusterMessagingConfig {

  @Bean
  public List<Declarable> topicBindings() {
    final Queue nodeReadyQueue = new Queue(NODE_READY_QUEUE.name(), false);
    final Queue nodeLivingQueue = new Queue(NODE_LIVING_QUEUE.name(), false);
    final Queue nodeInfoQueue = new Queue(NODE_INFO_QUEUE.name(), false);

    final TopicExchange topicExchange = new TopicExchange(NODE_LIFECYCLE_TOPIC.name());

    return Arrays.asList(
        nodeInfoQueue,
        nodeReadyQueue,
        nodeLivingQueue,
        topicExchange,
        BindingBuilder.bind(nodeInfoQueue).to(topicExchange).with(NODE_INFO_QUEUE.route()),
        BindingBuilder.bind(nodeReadyQueue).to(topicExchange).with(NODE_READY_QUEUE.route()),
        BindingBuilder.bind(nodeLivingQueue).to(topicExchange).with(NODE_LIVING_QUEUE.route()));
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }


}
