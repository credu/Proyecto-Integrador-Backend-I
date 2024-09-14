package com.example.clinicaodontologica.configs;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import com.example.clinicaodontologica.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigWebSecurity {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authz) -> authz
                        // Allowed endpoints
                        .requestMatchers("/index.html").permitAll()
                        // Required authentication endpoints
                        .requestMatchers(HttpMethod.GET,"/odontologo").authenticated()
                        .requestMatchers(HttpMethod.GET,"/paciente").authenticated()
                        // Admin endpoints
                        .requestMatchers("/get_odontologos.html").hasRole("ADMIN")
                        .requestMatchers("/get_pacientes.html").hasRole("ADMIN")
                        .requestMatchers("/get_turnos.html").hasRole("ADMIN")
                        .requestMatchers("/odontologo").hasRole("ADMIN")
                        .requestMatchers("/paciente").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/turno").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,    "/turno/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/turno").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/turno/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/odontologo/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/paciente/*").hasRole("ADMIN")
                        // User endpoints
                        .requestMatchers("/agendar_turnos.html").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/turno").hasRole("USER")
                        .anyRequest().permitAll()
                )
                //.formLogin(withDefaults())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login.html")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                );
        return http.build();
    }
}
