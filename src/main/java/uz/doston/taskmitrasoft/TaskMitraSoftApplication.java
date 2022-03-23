package uz.doston.taskmitrasoft;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import uz.doston.taskmitrasoft.property.OpenApiProperties;
import uz.doston.taskmitrasoft.property.ServerProperties;

@SpringBootApplication
@OpenAPIDefinition
@EnableConfigurationProperties(
        {OpenApiProperties.class, ServerProperties.class}
)
@EnableScheduling
public class TaskMitraSoftApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMitraSoftApplication.class, args);
    }

}
