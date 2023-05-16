package bootcamps.turkcell.inventoryservice.business.rules;


import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.inventoryservice.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModelBusinessRules {
    private final ModelRepository repository;

    public void modelNameCannotBeRepeated(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(ExceptionDetail.Messages.Model.NAME_ALREADY_EXISTS);
        }
    }
}
