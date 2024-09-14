package com.example.clinicaodontologica.configs;

import com.example.clinicaodontologica.enums.UsuarioRole;
import com.example.clinicaodontologica.models.Usuario;
import com.example.clinicaodontologica.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

@Component
public class DatosIniciales implements ApplicationRunner {
    private static final Logger logger = Logger.getLogger(DatosIniciales.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Usuario usuario1 = new Usuario("Administrador", "admin", "admin@admin.com", passwordEncoder.encode("1234"), UsuarioRole.ROLE_ADMIN);
        Usuario usuario2 = new Usuario("Usuario", "user", "user@user.com", passwordEncoder.encode("1234"), UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario1);
        logger.info("usuario administrador cargado con exito");
        usuarioRepository.save(usuario2);
        logger.info("usuario invitado cargado con exito");
    }}

