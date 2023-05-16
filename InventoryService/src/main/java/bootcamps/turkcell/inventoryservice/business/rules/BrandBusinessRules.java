package bootcamps.turkcell.inventoryservice.business.rules;

import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import bootcamps.turkcell.common.utilities.exceptions.business.BusinessException;
import bootcamps.turkcell.inventoryservice.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BrandBusinessRules {
    private final BrandRepository brandRepository;

    public void brandNameCannotBeRepeated(String name) {
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new BusinessException(ExceptionDetail.Messages.Brand.NAME_ALREADY_EXISTS);
        }
    }
}
