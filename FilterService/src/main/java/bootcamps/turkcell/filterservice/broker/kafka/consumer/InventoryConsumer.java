package bootcamps.turkcell.filterservice.broker.kafka.consumer;

import bootcamps.turkcell.common.events.inventory.BrandDeletedEvent;
import bootcamps.turkcell.common.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.events.inventory.CarDeletedEvent;
import bootcamps.turkcell.common.events.inventory.ModelDeletedEvent;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.utilities.formats.Information;
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
            topics = Topics.Inventory.CAR_CREATED
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapper.forRequest().map(event, CarFilter.class);
        carFilterService.add(filter);
        log.info(Information.Consume.log(event));
        //log.info(MessageFormat.format("Consumed: {0} --> ({1})", event.getClass().getSimpleName(), event));
    }

    @KafkaListener(
            groupId = "car-delete",
            topics = Topics.Inventory.CAR_DELETED
    )
    public void consume(CarDeletedEvent event) {
        carFilterService.deleteByCarId(event.getCarId());
        log.info(Information.Consume.log(event));
    }

    @KafkaListener(
            groupId = "brand-delete",
            topics = Topics.Inventory.BRAND_DELETED
    )
    public void consume(BrandDeletedEvent event) {
        carFilterService.deleteAllByBrandId(event.getBrandId());
        log.info(Information.Consume.log(event));
    }


    @KafkaListener(
            groupId = "model-delete",
            topics = Topics.Inventory.MODEL_DELETED
    )
    public void consume(ModelDeletedEvent event) {
        carFilterService.deleteAllByModelId(event.getModelId());
        log.info(Information.Consume.log(event));
    }
}
