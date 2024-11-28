package de.muenchen.oss.oai.pmh.starter.configuration;

import org.springdoc.core.customizers.RouterOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringdocParamsConfiguration {
    @Bean
    public RouterOperationCustomizer addRouterOperationCustomizer() {
        return (routerOperation, handlerMethod) -> {
            if (routerOperation.getParams().length > 0) {
                routerOperation.setPath(routerOperation.getPath() + "?" + String.join("&", routerOperation.getParams()));
            }
            return routerOperation;
        };
    }
}
