package App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Since H2 console runs within a frame so while Spring security is enabled, frame options has to be disabled explicitly, in order to get the H2 console working.
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/").authenticated();
                    auth.anyRequest().permitAll();
                })
                .httpBasic(withDefaults())
                .build();
    }

}