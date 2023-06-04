package bootcamps.turkcell.inventoryservice.business.managers;


import bootcamps.turkcell.common.models.events.inventory.CarCreatedEvent;
import bootcamps.turkcell.common.models.events.inventory.CarDeletedEvent;
import bootcamps.turkcell.common.models.events.inventory.CarStateUpdatedEvent;
import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.models.dtos.inventory.requests.car.create.CreateCarRequest;
import bootcamps.turkcell.common.models.dtos.inventory.requests.car.update.UpdateCarRequest;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.create.CreateCarResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.get.GetAllCarsResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.get.GetCarResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.car.update.UpdateCarResponse;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
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
    private final KafkaProducer producer;

    @Override
    public List<GetAllCarsResponse> getAll() {
        List<Car> cars = repository.findAll();
        return cars.stream().map(car -> mapper.forResponse().map(car, GetAllCarsResponse.class)).toList();
    }

    @Override
    public GetCarResponse getById(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        var car = repository.findById(id);
        return mapper.forResponse().map(car, GetCarResponse.class);
    }

    @Override
    public CreateCarResponse create(CreateCarRequest carRequest) {
        rules.licensePlateCannotBeRepeated(carRequest.getLicensePlate());
        Car car = mapper.forRequest().map(carRequest, Car.class);
        car.setState(CarState.AVAILABLE);
        var createdCar = repository.save(car);

        var event = mapper.forResponse().map(createdCar, CarCreatedEvent.class);
        producer.sendMessage(event, Topics.Inventory.CAR_CREATED);

        return mapper.forResponse().map(createdCar, CreateCarResponse.class);
    }

    @Override
    public UpdateCarResponse update(UUID id, UpdateCarRequest carRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        //rules.licensePlateCannotBeRepeated(carRequest.getLicensePlate());

        Car car = mapper.forRequest().map(carRequest, Car.class);
        car.setId(id);
        repository.save(car);
        return mapper.forResponse().map(car, UpdateCarResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        repository.deleteById(id);
        producer.sendMessage(new CarDeletedEvent(id), Topics.Inventory.CAR_DELETED);
    }

    @Override
    public void changeState(UUID id, CarState state) {
        Car car = repository.findById(id).orElseThrow();
        car.setState(state);
        repository.save(car);
        producer.sendMessage(new CarStateUpdatedEvent(id, state), Topics.Inventory.CAR_STATE_UPDATED);
    }

    /*@Override
    public ClientResponse checkIfCarAvailable(UUID id) {
        ClientResponse clientResponse = new ClientResponse();
        validateIfCarAvailable(id, clientResponse);

        return clientResponse;
    }*/

    /*private void validateIfCarAvailable(UUID id, ClientResponse response) {
        try {
            crudRules.idCannotBeProcessedWhenNotExists(id, repository);
            rules.carStateMustAvailable(id);
            response.setSuccess(true);
        } catch (BusinessException exception) {
            response.setSuccess(false);
            response.setMessage(exception.getMessage());
        }
    }*/
}
