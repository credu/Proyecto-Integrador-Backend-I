package com.example.clinicaodontologica.services;

import com.example.clinicaodontologica.models.Turno;
import com.example.clinicaodontologica.repositories.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public Turno buscarPorId(Long id){
        return turnoRepository.findById(id).orElse(null);
    }

    public void actualizarTurno(Turno turno){
        turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public List<Turno> listarTurnos(){
        return turnoRepository.findAll();
    }
}
