package bootcamps.turkcell.filterservice.api.controllers;

import bootcamps.turkcell.filterservice.business.dtos.responses.GetAllCarFiltersResponse;
import bootcamps.turkcell.filterservice.business.dtos.responses.GetCarFilterResponse;
import bootcamps.turkcell.filterservice.business.services.CarFilterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/car-filters")
public class CarFiltersController {
    private final CarFilterService service;

    @GetMapping
    public List<GetAllCarFiltersResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public GetCarFilterResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }
}
