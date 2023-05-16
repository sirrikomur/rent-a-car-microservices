package bootcamps.turkcell.common.configurations.mapper;

import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperManager;
import bootcamps.turkcell.common.utilities.mappers.modelmapper.ModelMapperService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapperService getModelMapperService(ModelMapper modelMapper) {
        return new ModelMapperManager(modelMapper);
    }
}
