package bootcamps.turkcell.inventoryservice.business.managers;

import bootcamps.turkcell.common.events.inventory.ModelDeletedEvent;
import bootcamps.turkcell.common.utilities.brokers.kafka.producers.KafkaProducer;
import bootcamps.turkcell.common.utilities.constants.Topics;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import bootcamps.turkcell.common.utilities.rules.CrudRules;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.model.create.CreateModelRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.requests.model.update.UpdateModelRequest;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.create.CreateModelResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.get.GetAllModelsResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.get.GetModelResponse;
import bootcamps.turkcell.inventoryservice.business.dtos.responses.model.update.UpdateModelResponse;
import bootcamps.turkcell.inventoryservice.business.rules.ModelBusinessRules;
import bootcamps.turkcell.inventoryservice.business.services.ModelService;
import bootcamps.turkcell.inventoryservice.domain.entities.Model;
import bootcamps.turkcell.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository repository;
    private final ModelBusinessRules rules;
    private final CrudRules crudRules;
    private final ModelMapperService mapper;
    private final KafkaProducer producer;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = repository.findAll();
        return models.stream().map(model -> this.mapper.forResponse().map(model, GetAllModelsResponse.class)).toList();
    }

    @Override
    public GetModelResponse getById(UUID id) {
        Model model = repository.findById(id).orElseThrow();
        return mapper.forResponse().map(model, GetModelResponse.class);
    }

    @Override
    public CreateModelResponse create(CreateModelRequest modelRequest) {
        rules.modelNameCannotBeRepeated(modelRequest.getName());
        Model model = mapper.forRequest().map(modelRequest, Model.class);
        model.setId(UUID.randomUUID());
        repository.save(model);
        return mapper.forResponse().map(model, CreateModelResponse.class);
    }

    @Override
    public UpdateModelResponse update(UUID id, UpdateModelRequest modelRequest) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);

        Model model = mapper.forRequest().map(modelRequest, Model.class);
        model.setId(id);
        repository.save(model);
        return mapper.forResponse().map(model, UpdateModelResponse.class);
    }

    @Override
    public void delete(UUID id) {
        crudRules.idCannotBeProcessedWhenNotExists(id, repository);
        repository.deleteById(id);
        producer.sendMessage(new ModelDeletedEvent(id), Topics.Inventory.MODEL_DELETED);
    }
}
