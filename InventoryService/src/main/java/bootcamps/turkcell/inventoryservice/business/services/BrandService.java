package bootcamps.turkcell.inventoryservice.business.services;

import bootcamps.turkcell.inventoryservice.business.dtos.requests.brand.create.CreateBrandRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.brand.update.UpdateBrandRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.brand.create.CreateBrandResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.brand.get.GetAllBrandsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.brand.get.GetBrandResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.brand.update.UpdateBrandResponse;

import java.util.List;
import java.util.UUID;

public interface BrandService {
    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(UUID id);
    CreateBrandResponse create(CreateBrandRequest brandRequest);
    UpdateBrandResponse update(UUID id, UpdateBrandRequest brandRequest);
    void delete(UUID id);
}
