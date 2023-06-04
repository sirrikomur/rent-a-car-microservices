package bootcamps.turkcell.maintenanceservice.api.controllers;

import bootcamps.turkcell.common.models.dtos.maintenance.requests.create.CreateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.requests.update.UpdateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.create.CreateMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetAllMaintenancesResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.update.UpdateMaintenanceResponse;
import bootcamps.turkcell.maintenanceservice.business.services.MaintenanceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/maintenances")
public class MaintenancesController {
    private final MaintenanceService service;

    @GetMapping
    List<GetAllMaintenancesResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    GetMaintenanceResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    CreateMaintenanceResponse create(@Valid @RequestBody CreateMaintenanceRequest maintenanceRequest) {
        return service.create(maintenanceRequest);
    }

    @PutMapping("/{id}")
    UpdateMaintenanceResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateMaintenanceRequest maintenanceRequest) {
        return service.update(id, maintenanceRequest);
    }

    @PutMapping("/{id}/finish")
    @ResponseStatus(HttpStatus.OK)
    GetMaintenanceResponse finishMaintenance(@PathVariable UUID id) {
        return service.finishMaintenance(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
