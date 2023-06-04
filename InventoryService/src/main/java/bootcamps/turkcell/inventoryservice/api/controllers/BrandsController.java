package bootcamps.turkcell.inventoryservice.api.controllers;

import bootcamps.turkcell.common.models.dtos.inventory.requests.brand.create.CreateBrandRequest;
import bootcamps.turkcell.common.models.dtos.inventory.requests.brand.update.UpdateBrandRequest;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.create.CreateBrandResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.get.GetAllBrandsResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.get.GetBrandResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.update.UpdateBrandResponse;
import bootcamps.turkcell.inventoryservice.business.services.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brands")
public class BrandsController {
    private final BrandService service;

    @GetMapping
    public List<GetAllBrandsResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse create(@Valid @RequestBody CreateBrandRequest brandRequest) {
        return service.create(brandRequest);
    }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable UUID id, @Valid @RequestBody UpdateBrandRequest brandRequest) {
        return service.update(id, brandRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}
