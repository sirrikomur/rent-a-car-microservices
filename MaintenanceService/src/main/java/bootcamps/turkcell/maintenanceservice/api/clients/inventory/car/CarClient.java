package bootcamps.turkcell.maintenanceservice.api.clients.inventory.car;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.get.GetCarClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = CarClientFallback.class)
public interface CarClient {

    @GetMapping("/api/cars/{carId}")
    GetCarClientResponse getById(@PathVariable UUID carId);

    @PutMapping("/api/cars/{id}/car-state")
    void changeState(@PathVariable UUID id, @RequestBody CarState carState);

    /*@GetMapping(value = "/api/cars/check-car-available/{carId}")
    @Retry(name = "check-if-car-available")
    ClientResponse<Entity> checkIfCarAvailable(@PathVariable UUID carId);*/

}
