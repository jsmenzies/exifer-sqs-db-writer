package io.menzies.exifer.config;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClient;
import io.awspring.cloud.messaging.config.QueueMessageHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import java.util.List;

@Configuration
public class AwsClientsConfig {

    @Bean
    @Primary
    public AmazonSQSAsync sqsClient() {
        return AmazonSQSAsyncClient.asyncBuilder().build();
    }

    @Bean
    public QueueMessageHandlerFactory queueMessageHandlerFactory() {
        QueueMessageHandlerFactory factory = new QueueMessageHandlerFactory();
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();

        //set strict content type match to false
        converter.setStrictContentTypeMatch(false);

        PayloadMethodArgumentResolver resolver = new PayloadMethodArgumentResolver(converter);
        factory.setArgumentResolvers(List.of(resolver));
        return factory;
    }
}

