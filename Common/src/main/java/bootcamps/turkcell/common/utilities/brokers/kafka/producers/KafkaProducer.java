package bootcamps.turkcell.common.utilities.brokers.kafka.producers;

import bootcamps.turkcell.common.models.events.Event;
import bootcamps.turkcell.common.utilities.formats.Information;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public <T extends Event> void sendMessage(T event, String topics) {
        log.info(Information.Produce.log(event, topics));
        Message<T> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topics)
                .build();

        kafkaTemplate.send(message);
    }
}
