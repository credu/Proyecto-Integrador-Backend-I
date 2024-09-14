package com.example.clinicaodontologica.controllers;

import com.example.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaodontologica.models.Odontologo;
import com.example.clinicaodontologica.services.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoService.buscarPorId(id);
        if (odontologo == null) {
            throw new ResourceNotFoundException("Odontologo no encontrado");
        }
        return ResponseEntity.ok(odontologo);
    }

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public void actualizarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.actualizarOdontologo(odontologo);
    }

    @DeleteMapping("/{id}")
    public void eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
    }
}
