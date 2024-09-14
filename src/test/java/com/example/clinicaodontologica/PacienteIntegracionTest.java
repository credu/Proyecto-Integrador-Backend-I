package com.example.clinicaodontologica;

import com.example.clinicaodontologica.models.Domicilio;
import com.example.clinicaodontologica.models.Paciente;
import com.example.clinicaodontologica.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false) //no necesito login en esta clase
public class PacienteIntegracionTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private MockMvc mockMvc;

    public void cargarPacientes(){
        Paciente paciente1= pacienteService.guardarPaciente(new Paciente("Matias","Santos","111111", LocalDate.of(2024,9,12), new Domicilio("Calle 1",122,"Uruguay","Montevideo"),"matiassantos@digitalhouse.com"));
        Paciente paciente2= pacienteService.guardarPaciente(new Paciente("Helen","Vasquez","1112221", LocalDate.of(2024,9,12), new Domicilio("Calle 2",122,"Lima","Peru"),"helenvasquez@digitalhouse.com"));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception{
        cargarPacientes();
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.get("/paciente").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = resultado.getResponse();
        assertFalse(response.getContentAsString().isEmpty());
    }
}
