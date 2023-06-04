package bootcamps.turkcell.maintenanceservice.business.services;

import bootcamps.turkcell.common.models.dtos.maintenance.requests.create.CreateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.requests.update.UpdateMaintenanceRequest;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.create.CreateMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetAllMaintenancesResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.get.GetMaintenanceResponse;
import bootcamps.turkcell.common.models.dtos.maintenance.responses.update.UpdateMaintenanceResponse;

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
