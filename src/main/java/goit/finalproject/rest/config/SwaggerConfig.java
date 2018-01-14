package goit.finalproject.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebSecurity
public class SwaggerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests().antMatchers("/", "/status/**").permitAll()
        .anyRequest().authenticated();

        httpSecurity.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication().withUser("user1").password("12345")
                .roles("USER");
    }

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
                ,new Contact("Group 1_8 (Dmytro Manzhula & Loban Tetyana), Inc", "www.goit.com"
                , "d.manzhula@gmail.com"),
                "manj license",
                "www.goit.com/license");

        return apiInfo;
    }
}
