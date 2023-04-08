package springboot.study.spring3ngrok.configs;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "false", matchIfMissing = true)
public class SecuritConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        Enable cors and disable CSRF
        httpSecurity = httpSecurity.cors().and().csrf().disable();

//        set session management to stateless
        httpSecurity = httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

        // set unauthorized requests exception handler
        httpSecurity.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }).and();

        //Auth server
//        httpSecurity.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthConverter);

        //set permission on end point
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

        return httpSecurity.build();
    }
}
