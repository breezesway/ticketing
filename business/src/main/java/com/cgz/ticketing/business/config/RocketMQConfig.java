package com.cgz.ticketing.business.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RocketMQConfig {

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    /**
     * 新版本需要声明RocketMQTemplate，否则会注入失败
     */
    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        RocketMQTemplate rocketMQTemplate = new RocketMQTemplate();
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(producerGroup);
        producer.setNamesrvAddr(nameServer);
        producer.setSendMsgTimeout(3000);
        rocketMQTemplate.setProducer(producer);
        return rocketMQTemplate;
    }
}
