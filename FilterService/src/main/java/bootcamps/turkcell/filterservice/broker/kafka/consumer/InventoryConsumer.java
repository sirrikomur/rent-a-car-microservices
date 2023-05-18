package bootcamps.turkcell.filterservice.broker.kafka.consumer;

import bootcamps.turkcell.common.events.inventory.BrandDeletedEvent;
import bootcamps.turkcell.common.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.events.inventory.CarDeletedEvent;
import bootcamps.turkcell.common.events.inventory.ModelDeletedEvent;
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
    private final ModelMapperService mapper;

    @KafkaListener(
            groupId = "car-create",
            topics = "car-created"
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapper.forRequest().map(event, CarFilter.class);
        carFilterService.add(filter);
        log.info("Consumed:CarCreatedEvent -> ({})", event);
    }

    @KafkaListener(
            groupId = "car-delete",
            topics = "car-deleted"
    )
    public void consume(CarDeletedEvent event) {
        carFilterService.deleteByCarId(event.getCarId());
        log.info("Consumed:CarDeletedEvent -> ({})", event);
    }

    @KafkaListener(
            groupId = "brand-delete",
            topics = "brand-deleted"
    )
    public void consume(BrandDeletedEvent event) {
        carFilterService.deleteAllByBrandId(event.getBrandId());
        log.info("Consumed:BrandDeletedEvent -> ({})", event);
    }


    @KafkaListener(
            groupId = "model-delete",
            topics = "model-deleted"
    )
    public void consume(ModelDeletedEvent event) {
        carFilterService.deleteAllByModelId(event.getModelId());
        log.info("Consumed:BrandDeletedEvent -> ({})", event);
    }
}
