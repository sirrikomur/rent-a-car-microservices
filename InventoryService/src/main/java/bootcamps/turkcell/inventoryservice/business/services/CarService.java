package bootcamps.turkcell.inventoryservice.business.services;

import bootcamps.turkcell.common.models.dtos.inventory.requests.car.create.CreateCarRequest;
import bootcamps.turkcell.common.models.dtos.inventory.requests.car.update.UpdateCarRequest;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.create.CreateCarResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.get.GetAllCarsResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.get.GetCarResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.update.UpdateCarResponse;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import java.util.List;
import java.util.UUID;

public interface CarService {
    List<GetAllCarsResponse> getAll();
    GetCarResponse getById(UUID id);
    CreateCarResponse create(CreateCarRequest carRequest);
    UpdateCarResponse update(UUID id, UpdateCarRequest carRequest);
    void delete(UUID id);

    void changeState(UUID id, CarState state);


    //ClientResponse checkIfCarAvailable(UUID id);
}
