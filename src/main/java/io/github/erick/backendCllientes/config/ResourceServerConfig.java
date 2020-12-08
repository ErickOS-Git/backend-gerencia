package io.github.erick.backendCllientes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig  extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/usuarios/**","/api/lancamentos/**").permitAll()
                .antMatchers("/api/clientes/**","/api/fornecedor/**", "/api/produtos/**",
                        "/api/tipo-usuario","/api/forma-pagamento/**","/api/empresa/**").authenticated()
                .anyRequest().denyAll();
    }
}
