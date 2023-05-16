package bootcamps.turkcell.inventoryservice.business.services;

import bootcamps.turkcell.inventoryservice.business.dtos.requests.model.create.CreateModelRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.model.update.UpdateModelRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.create.CreateModelResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.get.GetAllModelsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.get.GetModelResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.update.UpdateModelResponse;

import java.util.List;
import java.util.UUID;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(UUID id);
    CreateModelResponse create(CreateModelRequest modelRequest);
    UpdateModelResponse update(UUID id, UpdateModelRequest modelRequest);
    void delete(UUID id);
}
