package bootcamps.turkcell.maintenanceservice.api.clients.inventory.car;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.get.GetCarClientResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class CarClientFallback implements CarClient {
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

    /*@Override
    public ClientResponse checkIfCarAvailable(UUID carId) {
        log.info("INVENTORY SERVICE IS DOWN!");
        throw new RuntimeException("INVENTORY SERVICE IS DOWN!");
    }*/

}
