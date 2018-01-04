package goit.finalproject.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("goit.finalproject.rest.controller"))
                .paths(regex("/employee.*"))
                .build()
                .apiInfo(metaData());
    }

    public ApiInfo metaData(){
        ApiInfo apiInfo = new ApiInfo(
                "GoIt final project"
                ,"GoIt Restful service"
                , "1.0"
                ,"Terms of servise"
                ,new Contact("Dmytro Manzhula & Vart Art, Inc", "www.goit.com"
                , "d.manzhula@gmail.com"),
                "manj&vart license",
                "www.goit.com/license");

        return apiInfo;
    }
}
