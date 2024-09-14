package com.example.clinicaodontologica.services;

import com.example.clinicaodontologica.models.Domicilio;
import com.example.clinicaodontologica.models.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPaciente() {
        Paciente paciente = new Paciente("jorgito","Pereyra","1111111", LocalDate.of(2024,9,10), new Domicilio("Calle 1",12,"La Rioja","La Rioja"),"jorgito@jorgito.com");
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(1L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPaciente() {
        Long id=1L;
        Paciente pacienteBuscado = pacienteService.buscarPorId(id);
        assertNotNull(pacienteBuscado);
    }

    @Test
    @Order(3)
    public void actualizarPaciente(){
        Paciente pacienteBuscado = pacienteService.buscarPorId(1L);
        if(pacienteBuscado != null){
            pacienteBuscado.setApellido("Perez");
        }
        pacienteService.actualizarPaciente(pacienteBuscado);
        assertEquals("Perez",pacienteBuscado.getApellido());
    }

    @Test
    @Order(4)
    public void listarTodos(){
        List<Paciente> pacientes= pacienteService.listarTodos();
        assertEquals(1, pacientes.size());
    }

    @Test
    @Order(5)
    public void eliminaPaciente(){
        pacienteService.eliminarPaciente(1L);
        Paciente pacienteEliminado = pacienteService.buscarPorId(1L);
        assertFalse(pacienteEliminado != null);
    }
}
