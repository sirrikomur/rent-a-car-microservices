package bootcamps.turkcell.common.configurations.rule;

import bootcamps.turkcell.common.utilities.rules.CrudRules;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RulesConfiguration {

    @Bean
    public CrudRules getCrudRules() {
        return new CrudRules();
    }
}
