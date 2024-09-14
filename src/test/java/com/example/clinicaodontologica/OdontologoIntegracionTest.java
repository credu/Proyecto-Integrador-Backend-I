package com.example.clinicaodontologica;

import com.example.clinicaodontologica.models.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verificarEndpointAgregarOdontologo() throws Exception {
        Odontologo odontologo = new Odontologo("Ivonne", "Ona", "UxDesigner");
        String odontologoJSON = new ObjectMapper().writeValueAsString(odontologo);
        MvcResult resultado= mockMvc.perform(MockMvcRequestBuilders.post("/odontologo").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(odontologoJSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        MockHttpServletResponse response = resultado.getResponse();
        // Test1
        assertEquals(200, response.getStatus());
        // Test2
        assertFalse(response.getContentAsString().isEmpty());
    }
}
