package com.example.clinicaodontologica.services;

import com.example.clinicaodontologica.models.Odontologo;
import com.example.clinicaodontologica.repositories.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OdontologoService {
    @Autowired
    private OdontologoRepository odontologoRepository;

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public void actualizarOdontologo(Odontologo odontologo){
        boolean existeOdontologo = odontologoRepository.existsById(odontologo.getId());

        if (!existeOdontologo) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }

    public List<Odontologo> listarTodos(){
        return odontologoRepository.findAll();
    }

    public Odontologo buscarPorId(Long id){
        return odontologoRepository.findById(id).orElse(null);
    }
}
