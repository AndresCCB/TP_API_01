package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Repositories.AdministradorRepository;
import com.example.TP_API_01.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final AdministradorRepository administradorRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository, AdministradorRepository administradorRepository) {
        this.personaRepository = personaRepository;
        this.administradorRepository = administradorRepository;
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


    public void RegistroUsuario(String Documento, String Mail, String Password) throws PersonaException {
        Persona persona= buscarPersona(Documento);
        persona.setMail(Mail);
        persona.cambiarPassword(Password);
        personaRepository.save(persona);
    }

    public Boolean ValidacionIngreso(String Mail, String Password){
        return personaRepository.findByMailAndPassword(Mail, Password).isPresent();
    }

    public Boolean esAdmin(String Mail, String Password){
        Persona persona= personaRepository.findByMailAndPassword(Mail, Password).get();
        return administradorRepository.findByDocumento(persona.getDocumento()).isPresent();
    }
}
