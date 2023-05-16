package bootcamps.turkcell.common.utilities.rules;

import bootcamps.turkcell.common.utilities.constants.ExceptionDetail;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CrudRules {

    public <T> void idCannotBeProcessedWhenNotExists(UUID id, CrudRepository<T, UUID> repository) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(ExceptionDetail.Messages.Base.ID_NOT_EXISTS);
        }
    }
}
