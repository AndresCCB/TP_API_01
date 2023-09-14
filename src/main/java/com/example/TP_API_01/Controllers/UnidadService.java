package com.example.TP_API_01.Controllers;


import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Repositories.PersonaRepository;
import com.example.TP_API_01.Repositories.UnidadRepository;
import com.example.TP_API_01.Views.PersonaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadService {

    private final UnidadRepository unidadRepository;

    @Autowired
    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepository personaRepository;



    public Unidad buscarUnidad(Integer codigo, String piso, String numero) throws UnidadException {
        Optional<Unidad> optionalEdificio = unidadRepository.findByEdificioCodigoAndPisoAndNumero(codigo, piso, numero);

        if (optionalEdificio.isPresent()) {
            return optionalEdificio.get();
        } else {
            throw new UnidadException("La unidad con código de edificio " + codigo + ", piso " + piso + " y número " + numero + " no existe.");
        }
    }

    public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        List<PersonaView> resultado = new ArrayList<PersonaView>();
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        List<Persona> duenios = unidad.getDuenios();
        for(Persona persona : duenios)
            resultado.add(persona.toView());
        return resultado;
    }

    public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
        List<PersonaView> resultado = new ArrayList<PersonaView>();
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        List<Persona> inquilinos = unidad.getInquilinos();
        for(Persona persona : inquilinos)
            resultado.add(persona.toView());
        return resultado;
    }

    public void transferirUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        Persona persona = personaService.buscarPersona(documento);
        unidad.transferir(persona);
        unidadRepository.save(unidad);
        personaRepository.save(persona);
    }

    public void agregarDuenioUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException {
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        Persona persona = personaService.buscarPersona(documento);
        unidad.agregarDuenio(persona);
        unidadRepository.save(unidad);
        personaRepository.save(persona);
    }

    public void alquilarUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        Persona persona = personaService.buscarPersona(documento);
        unidad.alquilar(persona);
        unidadRepository.save(unidad);
        personaRepository.save(persona);
    }

    public void agregarInquilinoUnidad(int codigo, String piso, String numero, String documento) throws UnidadException, PersonaException{
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        Persona persona = personaService.buscarPersona(documento);
        unidad.agregarInquilino(persona);
        unidadRepository.save(unidad);
        personaRepository.save(persona);
    }

    public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        unidad.liberar();
        unidadRepository.save(unidad);
    }

    public void habitarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        unidad.habitar();
        unidadRepository.save(unidad);
    }


}
