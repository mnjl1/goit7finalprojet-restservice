//package goit.finalproject.rest.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//@EnableWebSecurity
//public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER")
//                .and().withUser("admin1").password("qwerty").roles("USER", "ADMIN");
//    }
//
//
//    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.httpBasic().and().authorizeRequests().antMatchers("/employee")
//                .hasRole("USER").antMatchers("/**").hasRole("ADMIN")
//                .and().csrf().disable().headers();
//    }
//
//}
