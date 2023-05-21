package bootcamps.turkcell.rentalservice.api.clients.inventory;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.rentalservice.api.clients.inventory.car.CarClient;
import bootcamps.turkcell.rentalservice.business.dtos.responses.clients.GetCarClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class InventoryClientFallback implements CarClient {
    @Override
    public GetCarClientResponse getById(UUID carId) {
        log.info("Inventory Service: DISABLED!");
        throw new RuntimeException("Inventory Service: DISABLED!");
    }

    @Override
    public void changeState(UUID id, CarState carState) {
        log.info("Inventory Service: DISABLED!");
        throw new RuntimeException("Inventory Service: DISABLED!");
    }
}
