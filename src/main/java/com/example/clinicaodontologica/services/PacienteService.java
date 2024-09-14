package com.example.clinicaodontologica.services;

import com.example.clinicaodontologica.models.Paciente;
import com.example.clinicaodontologica.models.Paciente;
import com.example.clinicaodontologica.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void actualizarPaciente(Paciente paciente){
        Paciente pacienteEncontrado = buscarPorId(paciente.getId());

        if (pacienteEncontrado == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        long idDomicilio = pacienteEncontrado.getDomicilio().getId();
        paciente.getDomicilio().setId(idDomicilio);

        pacienteRepository.save(paciente);
    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> listarTodos(){
        return pacienteRepository.findAll();
    }

    public Paciente buscarPorId(Long id){
        return pacienteRepository.findById(id).orElse(null);
    }
}
