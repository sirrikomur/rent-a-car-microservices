package bootcamps.turkcell.filterservice.broker.kafka.consumer;

import bootcamps.turkcell.common.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.filterservice.business.services.CarFilterService;
import bootcamps.turkcell.filterservice.domain.entities.CarFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryConsumer {
    private final CarFilterService carFilterService;
    @KafkaListener(
            topics = "car-created",
            groupId = "car-create"
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapper.forRequest().map(event, CarFilter.class);
        carFilterService.add(filter);
        log.info("Car created event consumed {}", event);
    }

    private final ModelMapperService mapper;
}
