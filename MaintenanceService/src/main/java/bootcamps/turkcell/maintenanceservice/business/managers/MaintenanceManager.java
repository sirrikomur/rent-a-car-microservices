package bootcamps.turkcell.maintenanceservice.business.managers;


import bootcamps.turkcell.common.models.dtos.maintenance.requests.create.CreateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.requests.update.UpdateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.create.CreateMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetAllMaintenancesResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.update.UpdateMaintenanceResponse;
import bootcamps.turkcell.common.models.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.maintenanceservice.api.clients.InventoryServiceClient;
import bootcamps.turkcell.maintenanceservice.business.rules.MaintenanceBusinessRules;
import bootcamps.turkcell.maintenanceservice.business.services.MaintenanceService;
import bootcamps.turkcell.maintenanceservice.domain.entities.Maintenance;
import bootcamps.turkcell.maintenanceservice.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository repository;
    private final MaintenanceBusinessRules rules;
    private final CrudRules crudRules;
    private final ModelMapperService mapper;
    private final InventoryServiceClient inventoryServiceClient;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = repository.findAll();
        return maintenances.stream().map(maintenance -> mapper.forResponse().map(maintenance, GetAllMaintenancesResponse.class)).toList();
    }

    @Override
    public GetMaintenanceResponse getById(UUID id) {
        Maintenance maintenance = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);
    }

    @Override
    public CreateMaintenanceResponse create(CreateMaintenanceRequest request) {
        var car = inventoryServiceClient.getById(request.getCarId());
        rules.carCannotBePutUnderMaintenanceWhenUnderMaintenance(request.getCarId());
        rules.carCannotBePutUnderMaintenanceWhenNotAvailable(car.getCarState());
        Maintenance maintenance = mapper.forRequest().map(request, Maintenance.class);
        maintenance.setStartDate(LocalDateTime.now());
        repository.save(maintenance);

        inventoryServiceClient.changeState(request.getCarId(), CarState.MAINTENANCE);
        return mapper.forResponse().map(maintenance, CreateMaintenanceResponse.class);
    }

    @Override
    public UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest maintenanceRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        Maintenance maintenance = mapper.forRequest().map(maintenanceRequest, Maintenance.class);
        maintenance.setId(id);
        repository.save(maintenance);
        return mapper.forResponse().map(maintenance, UpdateMaintenanceResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        var carId = repository.findById(id).orElseThrow().getCarId();
        inventoryServiceClient.changeState(carId, CarState.AVAILABLE);
        repository.deleteById(id);
    }

    @Override
    public GetMaintenanceResponse finishMaintenance(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        Maintenance maintenance = repository.findById(id).orElseThrow();
        rules.carCannotBeFinishedWhenNotUnderMaintenance(maintenance.getCarId());

        maintenance.setEndDate(LocalDateTime.now());
        repository.save(maintenance);

        inventoryServiceClient.changeState(maintenance.getCarId(), CarState.AVAILABLE);
        return mapper.forResponse().map(maintenance, GetMaintenanceResponse.class);
    }
}
