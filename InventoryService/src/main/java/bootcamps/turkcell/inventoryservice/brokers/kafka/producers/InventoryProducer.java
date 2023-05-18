package bootcamps.turkcell.inventoryservice.brokers.kafka.producers;

import bootcamps.turkcell.common.events.inventory.BrandDeletedEvent;
import bootcamps.turkcell.common.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.events.inventory.CarDeletedEvent;
import bootcamps.turkcell.common.events.inventory.ModelDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(CarCreatedEvent event) {
        log.info(String.format("car-created event => %s", event.toString()));
        Message<CarCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-created")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarDeletedEvent event) {
        log.info(String.format("car-deleted event => %s", event.toString()));
        Message<CarDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-deleted")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(BrandDeletedEvent event) {
        log.info(String.format("car-deleted event => %s", event.toString()));
        Message<BrandDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "brand-deleted")
                .build();

        kafkaTemplate.send(message);
    }

    public void sendMessage(ModelDeletedEvent event) {
        log.info(String.format("car-deleted event => %s", event.toString()));
        Message<ModelDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "model-deleted")
                .build();

        kafkaTemplate.send(message);
    }
}
