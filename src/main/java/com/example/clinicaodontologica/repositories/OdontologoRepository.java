package com.example.clinicaodontologica.repositories;

import com.example.clinicaodontologica.models.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Odontologo findByMatricula(String matricula);
}
