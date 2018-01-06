package goit.finalproject.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket employeeApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("employee")
                .select()
                .apis(RequestHandlerSelectors.basePackage("goit.finalproject.rest." +
                        "controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    public ApiInfo metaData(){
        ApiInfo apiInfo = new ApiInfo(
                "GoIt final project"
                ,"GoIt Restful service"
                , "1.0"
                ,"Terms of service"
                ,new Contact("Dmytro Manzhula, Inc", "www.goit.com"
                , "d.manzhula@gmail.com"),
                "manj&vart license",
                "www.goit.com/license");

        return apiInfo;
    }
}
