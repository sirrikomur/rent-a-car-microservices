package bootcamps.turkcell.inventoryservice.api.controllers;

import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.create.CreateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.update.UpdateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.create.CreateCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetAllCarsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.update.UpdateCarResponse;
import bootcamps.turkcell.inventoryservice.business.services.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;

    @GetMapping
    public List<GetAllCarsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetCarResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public CreateCarResponse create(@Valid @RequestBody CreateCarRequest carRequest) {
        return service.create(carRequest);
    }

    @PutMapping("/{id}")
    public UpdateCarResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateCarRequest carRequest) {
        return service.update(id, carRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
