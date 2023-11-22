package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Repositories.AdministradorRepository;
import com.example.TP_API_01.Repositories.PersonaRepository;
import com.example.TP_API_01.Repositories.ReclamoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final AdministradorRepository administradorRepository;
    private final ReclamoRepository reclamoRepository;



    @Autowired
    public PersonaService(PersonaRepository personaRepository, AdministradorRepository administradorRepository, ReclamoRepository reclamoRepository) {
        this.personaRepository = personaRepository;
        this.administradorRepository = administradorRepository;
        this.reclamoRepository= reclamoRepository;

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
    @Transactional
    public void eliminarPersona(String documento) throws PersonaException{

        Persona persona = buscarPersona(documento);

        if (persona != null) {
            reclamoRepository.deleteByPersona(persona);

            personaRepository.delete(persona);
        } else {
            throw new PersonaException("Persona no encontrada");
        }
    }

    public List<Persona> listarPersonas(){
        return personaRepository.findAll();
    }


    public void RegistroUsuario(String Documento, String Mail, String Password) throws PersonaException {
        Optional<Persona> persona= personaRepository.findById(Documento);
        if(persona.isPresent()){
            persona.get().setMail(Mail);
            persona.get().cambiarPassword(Password);
            personaRepository.save(persona.get());
        }else{throw new PersonaException("La persona no existe");}

    }

    public Boolean ValidacionIngreso(String Mail, String Password){
        return personaRepository.findByMailAndContrasenia(Mail, Password) != null;


    }

    public Boolean esAdmin(String Mail, String Password){
        Persona persona= personaRepository.findByMailAndContrasenia(Mail, Password);
        return administradorRepository.findByDocumento_Documento(persona.getDocumento())!= null;
    }
}
