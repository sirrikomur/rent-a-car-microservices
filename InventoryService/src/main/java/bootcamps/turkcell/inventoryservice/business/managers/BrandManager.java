package bootcamps.turkcell.inventoryservice.business.managers;

import bootcamps.turkcell.common.models.events.inventory.BrandDeletedEvent;
import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.models.dtos.inventory.requests.brand.create.CreateBrandRequest;
import bootcamps.turkcell.common.models.dtos.inventory.requests.brand.update.UpdateBrandRequest;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.create.CreateBrandResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.get.GetAllBrandsResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.get.GetBrandResponse;
import bootcamps.turkcell.common.models.dtos.inventory.responses.brand.update.UpdateBrandResponse;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.inventoryservice.business.rules.BrandBusinessRules;
import bootcamps.turkcell.inventoryservice.business.services.BrandService;
import bootcamps.turkcell.inventoryservice.domain.entities.Brand;
import bootcamps.turkcell.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository repository;
    private final BrandBusinessRules rules;
    private final CrudRules crudRules;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = repository.findAll();
        return brands.stream().map(brand -> mapper.forResponse().map(brand, GetAllBrandsResponse.class)).toList();
    }

    @Override
    public GetBrandResponse getById(UUID id) {
        Brand brand = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(brand, GetBrandResponse.class);
    }

    @Override
    public CreateBrandResponse create(CreateBrandRequest brandRequest) {
        rules.brandNameCannotBeRepeated(brandRequest.getName());

        Brand brand = mapper.forRequest().map(brandRequest, Brand.class);
        var createdBrand = repository.save(brand);

        return mapper.forResponse().map(createdBrand, CreateBrandResponse.class);
    }

    @Override
    public UpdateBrandResponse update(UUID id, UpdateBrandRequest brandRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        rules.brandNameCannotBeRepeated(brandRequest.getName());

        Brand brand = mapper.forRequest().map(brandRequest, Brand.class);
        brand.setId(id);
        repository.save(brand);

        return mapper.forResponse().map(brand, UpdateBrandResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        repository.deleteById(id);
        producer.sendMessage(new BrandDeletedEvent(id), Topics.Inventory.BRAND_DELETED);
    }
}
