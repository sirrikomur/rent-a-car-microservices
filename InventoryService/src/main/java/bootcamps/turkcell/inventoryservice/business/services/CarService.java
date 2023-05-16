package bootcamps.turkcell.inventoryservice.business.services;

import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.create.CreateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.update.UpdateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.create.CreateCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetAllCarsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.update.UpdateCarResponse;

import java.util.List;
import java.util.UUID;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(UUID id);
    CreateCarResponse create(CreateCarRequest carRequest);
    UpdateCarResponse update(UUID id, UpdateCarRequest carRequest);
    void delete(UUID id);
}
