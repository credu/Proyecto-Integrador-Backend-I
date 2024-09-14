package com.example.clinicaodontologica.controllers;

import com.example.clinicaodontologica.exceptions.ResourceBadRequestException;
import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.models.Odontologo;
import com.example.clinicaodontologica.models.Paciente;
import com.example.clinicaodontologica.models.Turno;
import com.example.clinicaodontologica.services.OdontologoService;
import com.example.clinicaodontologica.services.PacienteService;
import com.example.clinicaodontologica.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Turno turno = turnoService.buscarPorId(id);
        if (turno == null) {
            throw new ResourceNotFoundException("Turno no encontrado");
        }
        return ResponseEntity.ok(turno);
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws ResourceBadRequestException {
        Paciente pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado == null || odontologoBuscado == null) {
            throw new ResourceBadRequestException("Paciente o odontologo no encontrado");
        }

        Turno turnoAgregado = turnoService.guardarTurno(turno);
        turnoAgregado.setPaciente(pacienteBuscado);
        turnoAgregado.setOdontologo(odontologoBuscado);
        return ResponseEntity.ok(turnoAgregado);
    }

    @PutMapping
    public void editarTurno(@RequestBody Turno turno) throws ResourceBadRequestException {
        Paciente pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado == null || odontologoBuscado == null) {
            throw new ResourceBadRequestException("Paciente o odontologo no encontrado");
        }

        turnoService.actualizarTurno(turno);
    }

    @DeleteMapping("/{id}")
    public void eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
    }
}
