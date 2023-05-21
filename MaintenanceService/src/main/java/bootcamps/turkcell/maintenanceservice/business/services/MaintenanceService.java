package bootcamps.turkcell.maintenanceservice.business.services;


import bootcamps.turkcell.maintenanceservice.business.dtos.requests.create.CreateMaintenanceRequest;
import bootcamps.turkcell.maintenanceservice.business.dtos.requests.update.UpdateMaintenanceRequest;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.create.CreateMaintenanceResponse;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.get.GetAllMaintenancesResponse;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.get.GetMaintenanceResponse;
import bootcamps.turkcell.maintenanceservice.business.dtos.responses.update.UpdateMaintenanceResponse;

import java.util.List;
import java.util.UUID;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(UUID id);
    GetMaintenanceResponse finishMaintenance(UUID id);
    CreateMaintenanceResponse create(CreateMaintenanceRequest maintenanceRequest);
    UpdateMaintenanceResponse update(UUID id, UpdateMaintenanceRequest maintenanceRequest);
    void delete(UUID id);
}
