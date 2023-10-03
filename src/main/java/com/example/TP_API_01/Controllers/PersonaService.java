package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona buscarPersona(String documento) throws PersonaException {
        Optional<Persona> persona = personaRepository.findById(documento);

        if (persona.isPresent()){
            return persona.get();
        }else {throw new PersonaException("La persona con el documento" + documento + "no existe");}
    }

    public Persona agregarPersona(String documento, String nombre){
        Persona persona = new Persona(documento, nombre, null, null);
        return personaRepository.save(persona);

    }

    public void eliminarPersona(String documento) throws PersonaException{
        Persona persona = buscarPersona(documento);
        personaRepository.delete(persona);
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }

}
