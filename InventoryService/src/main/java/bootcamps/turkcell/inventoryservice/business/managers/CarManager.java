package bootcamps.turkcell.inventoryservice.business.managers;


import bootcamps.turkcell.common.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.inventoryservice.brokers.kafka.producers.InventoryProducer;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.create.CreateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.car.update.UpdateCarRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.create.CreateCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetAllCarsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.get.GetCarResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.car.update.UpdateCarResponse;
import bootcamps.turkcell.inventoryservice.business.rules.CarBusinessRules;
import bootcamps.turkcell.inventoryservice.business.services.CarService;
import bootcamps.turkcell.inventoryservice.domain.entities.Car;
import bootcamps.turkcell.inventoryservice.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository repository;
    private final CarBusinessRules rules;
    private final CrudRules crudRules;
    private final ModelMapperService mapper;

    private final InventoryProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        return cars.stream().map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class)).toList();
    }

    @Override
    public GetCarResponse getById(UUID id) {
        Car car = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse create(CreateCarRequest carRequest) {
        rules.licensePlateCannotBeRepeated(carRequest.getLicensePlate());
        Car car = mapper.forRequest().map(carRequest, Car.class);
        car.setId(UUID.randomUUID());
        car.setState(CarState.AVAILABLE);
        var createdCar = repository.save(car);

        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event);

        return mapper.forResponse().map(createdCar, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest carRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        rules.licensePlateCannotBeRepeated(carRequest.getLicensePlate());

        Car car = mapper.forRequest().map(carRequest, Car.class);
        car.setId(id);
        repository.save(car);
        return mapper.forResponse().map(car, UpdateCarResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        repository.deleteById(id);
    }
}
