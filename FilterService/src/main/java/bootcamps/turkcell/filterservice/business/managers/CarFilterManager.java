package bootcamps.turkcell.filterservice.business.managers;

import bootcamps.turkcell.common.utilities.enums.inventory.CarState;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.filterservice.business.dtos.responses.GetAllCarFiltersResponse;
import bootcamps.turkcell.filterservice.business.dtos.responses.GetCarFilterResponse;
import bootcamps.turkcell.filterservice.business.services.CarFilterService;
import bootcamps.turkcell.filterservice.domain.entities.CarFilter;
import bootcamps.turkcell.filterservice.repository.CarFilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarFilterManager implements CarFilterService {
    private final CarFilterRepository repository;
    private final ModelMapperService mapper;

    @Override
    public GetCarFilterResponse getById(UUID id) {
        var filter = repository.findById(id);
        return mapper.forResponse().map(filter, GetCarFilterResponse.class);
    }

    @Override
    public List<GetAllCarFiltersResponse> getAll() {
        var filters = repository.findAll();
        return filters.stream().map(carFilter -> mapper.forResponse().map(carFilter, GetAllCarFiltersResponse.class)).toList();
    }

    @Override
    public CarFilter getByCarId(UUID carId) {
        return repository.findByCarId(carId);
    }

    @Override
    public void add(CarFilter filter) {
        filter.setId(UUID.randomUUID());
        repository.save(filter);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByCarId(UUID carId) {
        repository.deleteByCarId(carId);
    }

    @Override
    public void deleteAllByBrandId(UUID brandId) {
        repository.deleteAllByBrandId(brandId);
    }

    @Override
    public void deleteAllByModelId(UUID modelId) {
        repository.deleteAllByModelId(modelId);
    }

    @Override
    public void updateCarStateByCarId(UUID carId, CarState carState) {
        var filter = repository.findByCarId(carId);
        filter.setCarState(carState);
        repository.save(filter);
    }
}
