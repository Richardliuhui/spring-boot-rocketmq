package com.zhizus.spring.boot.rocketmq;

import com.zhizus.spring.boot.rocketmq.annotation.RocketMQMessageListener;
import com.zhizus.spring.boot.rocketmq.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * Created by dempezheng on 2017/12/28.
 */
@SpringBootApplication
public class ConsumerApplication {

    private final static Logger log = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
    public class MyConsumer1 implements RocketMQListener<String> {
        public void onMessage(String message) {
            log.info("received message: {}", message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
    public class MyConsumer2 implements RocketMQListener<ProducerApplication.OrderPaidEvent> {
        public void onMessage(ProducerApplication.OrderPaidEvent orderPaidEvent) {
            log.info("received orderPaidEvent: {}", orderPaidEvent);
        }
    }
}
