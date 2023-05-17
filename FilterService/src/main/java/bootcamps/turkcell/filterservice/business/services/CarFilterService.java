package bootcamps.turkcell.filterservice.business.services;

import bootcamps.turkcell.filterservice.business.dtos.responses.GetAllCarFiltersResponse;
import bootcamps.turkcell.filterservice.business.dtos.responses.GetCarFilterResponse;
import bootcamps.turkcell.filterservice.domain.entities.CarFilter;

import java.util.List;
import java.util.UUID;

public interface CarFilterService {
    GetCarFilterResponse getById(UUID id);
    List<GetAllCarFiltersResponse> getAll();
    CarFilter getByCarId(UUID carId);
    void add(CarFilter filter);
    void deleteById(UUID id);
    void deleteByCarId(UUID carId);
    void deleteAllByBrandId(UUID brandId);
    void deleteAllByModelId(UUID modelId);
}