package bootcamps.turkcell.common.configurations.broker.kafka.producer;

import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfiguration {

    @Bean
    public KafkaProducer getKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate);
    }
}
