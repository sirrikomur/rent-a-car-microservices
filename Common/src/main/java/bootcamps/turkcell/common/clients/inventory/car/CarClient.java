package bootcamps.turkcell.common.clients.inventory.car;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "inventory-service", fallback = CarClientFallback.class)
public interface CarClient {

    @GetMapping("/api/cars/{id}")
    GetCarResponse getById(@PathVariable UUID id);

    @PutMapping("/api/cars/{id}/car-state")
    void changeState(@PathVariable UUID id, @RequestBody CarState carState);
}