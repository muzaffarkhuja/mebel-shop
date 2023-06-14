package uz.home.mebelshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("asad_bek_0201")
                .password(passwordEncoder().encode("pass"))
                .authorities("ADMIN");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .csrf()
               .disable()
               .authorizeHttpRequests()
               .requestMatchers(HttpMethod.GET,"/product/**", "/category/**","/image/**", "/sales/**", "/order/**")
               .permitAll()
               .requestMatchers( "/v3/api-docs/**","/swagger-ui/**", "/swagger-ui/index.html","/v3/api-docs.yaml")
               .permitAll()
               .anyRequest()
               .authenticated()
               .and()
               .httpBasic()
               .and().build();


    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
