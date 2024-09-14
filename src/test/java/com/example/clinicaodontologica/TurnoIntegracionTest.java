package com.example.clinicaodontologica;

import com.example.clinicaodontologica.models.Domicilio;
import com.example.clinicaodontologica.models.Odontologo;
import com.example.clinicaodontologica.models.Paciente;
import com.example.clinicaodontologica.models.Turno;
import com.example.clinicaodontologica.services.OdontologoService;
import com.example.clinicaodontologica.services.PacienteService;
import com.example.clinicaodontologica.services.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //no necesito login en esta clase
public class TurnoIntegracionTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarTurnos(){
        Paciente paciente1= pacienteService.guardarPaciente(new Paciente("Matias","Santos","111111", LocalDate.of(2024,9,12), new Domicilio("Calle 1",122,"Uruguay","Montevideo"),"matiassantos@digitalhouse.com"));
        Paciente paciente2= pacienteService.guardarPaciente(new Paciente("Helen","Vasquez","1112221", LocalDate.of(2024,9,12), new Domicilio("Calle 2",122,"Lima","Peru"),"helenvasquez@digitalhouse.com"));
        Odontologo odontologo1= odontologoService.guardarOdontologo(new Odontologo("Juan","Maldonado","MP10"));
        Odontologo odontologo2= odontologoService.guardarOdontologo(new Odontologo("Daniela","Paz","MP20"));
        Turno turno1 = turnoService.guardarTurno(new Turno(paciente1, odontologo1, LocalDate.of(2024,11,12)));
        Turno turno2 = turnoService.guardarTurno(new Turno(paciente2, odontologo2, LocalDate.of(2024,12,20)));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarTurnos();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/turno").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(resultado.getResponse().getContentAsString().isEmpty());

    }
}
