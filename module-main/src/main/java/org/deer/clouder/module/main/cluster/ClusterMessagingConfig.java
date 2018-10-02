package org.deer.clouder.module.main.cluster;

import static org.deer.clouder.api.message.queue.Queues.SYSTEM_QUEUE;

import org.deer.clouder.api.message.topic.SystemQueueTopic;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClusterMessagingConfig {

    @Bean
    Queue queue() {
        return new Queue(SYSTEM_QUEUE.name(), false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(SystemQueueTopic.NODE_LIFECYCLE_EVENT.name());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("org.deer.#");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
        MessageListenerAdapter listenerAdapter,
        MessageConverter converter) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(SYSTEM_QUEUE.name());
        container.setMessageListener(listenerAdapter);
        container.setMessageConverter(converter);
        return container;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    MessageListenerAdapter listenerAdapter(ClusterStateMonitor receiver, MessageConverter converter) {
        final MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(receiver, "onNodeReady");
        listenerAdapter.setMessageConverter(converter);
        return listenerAdapter;
    }
}
