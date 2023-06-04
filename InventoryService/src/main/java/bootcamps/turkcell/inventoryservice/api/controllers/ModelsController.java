package bootcamps.turkcell.inventoryservice.api.controllers;

import bootcamps.turkcell.common.models.dtos.inventory.requests.model.create.CreateModelRequest;
import bootcamps.turkcell.common.models.dtos.inventory.requests.model.update.UpdateModelRequest;
import bootcamps.turkcell.common.models.dtos.inventory.responses.model.create.CreateModelResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.model.get.GetAllModelsResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.model.get.GetModelResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.model.update.UpdateModelResponse;
import bootcamps.turkcell.inventoryservice.business.services.ModelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService service;

    @GetMapping
    public List<GetAllModelsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateModelResponse create(@Valid @RequestBody CreateModelRequest modelRequest) {
        return service.create(modelRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateModelResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateModelRequest modelRequest) {
        return service.update(id, modelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
