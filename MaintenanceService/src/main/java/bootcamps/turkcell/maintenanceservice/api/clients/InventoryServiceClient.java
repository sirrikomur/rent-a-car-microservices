package bootcamps.turkcell.maintenanceservice.api.clients;

import bootcamps.turkcell.common.models.dtos.inventory.responses.car.get.GetCarResponse;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "inventory-service", path = "/api")
public interface InventoryServiceClient {
    Logger logger = LoggerFactory.getLogger(InventoryServiceClient.class);

    @GetMapping("/cars/{id}")
    @CircuitBreaker(name = "getByIdCircuitBreaker", fallbackMethod = "getByIdFallback")
    GetCarResponse getById(@PathVariable UUID id);

    @PutMapping("/cars/{id}/car-state")
    @CircuitBreaker(name = "changeStateCircuitBreaker", fallbackMethod = "changeStateFallback")
    void changeState(@PathVariable UUID id, @RequestBody CarState carState);

    default GetCarResponse getByIdFallback(UUID id, Exception exception) {
        logger.info("Inventory Service is down!");
        throw new RuntimeException(exception.getMessage());
    }

    default void changeStateFallback(UUID id, CarState carState, Exception exception) {
        logger.info("Inventory Service is down!");
    }
}
