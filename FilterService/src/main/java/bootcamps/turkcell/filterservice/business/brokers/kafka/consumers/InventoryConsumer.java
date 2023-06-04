package bootcamps.turkcell.filterservice.business.brokers.kafka.consumers;

import bootcamps.turkcell.common.models.events.inventory.*;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.utilities.formats.Information;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.filterservice.business.services.CarFilterService;
import bootcamps.turkcell.filterservice.domain.entities.InventoryFilter;
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

    /* CAR */
    @KafkaListener(
            groupId = "car-create",
            topics = Topics.Inventory.CAR_CREATED
    )
    public void consume(CarCreatedEvent event) {
        var filter = mapper.forRequest().map(event, InventoryFilter.class);
        carFilterService.add(filter);
        log.info(Information.Consume.log(event));
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
            groupId = "car-state-update",
            topics = Topics.Inventory.CAR_STATE_UPDATED
    )
    public void consume(CarStateUpdatedEvent event) {
        carFilterService.updateCarStateByCarId(event.getCarId(), event.getCarState());
        log.info(Information.Consume.log(event));
    }

    /* BRAND */
    @KafkaListener(
            groupId = "brand-delete",
            topics = Topics.Inventory.BRAND_DELETED
    )
    public void consume(BrandDeletedEvent event) {
        carFilterService.deleteAllByBrandId(event.getBrandId());
        log.info(Information.Consume.log(event));
    }

    /* MODEL */
    @KafkaListener(
            groupId = "model-delete",
            topics = Topics.Inventory.MODEL_DELETED
    )
    public void consume(ModelDeletedEvent event) {
        carFilterService.deleteAllByModelId(event.getModelId());
        log.info(Information.Consume.log(event));
    }
}
