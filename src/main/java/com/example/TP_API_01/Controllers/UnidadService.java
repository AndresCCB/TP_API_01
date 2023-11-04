package com.example.TP_API_01.Controllers;


import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Repositories.PersonaRepository;
import com.example.TP_API_01.Repositories.UnidadRepository;
import com.example.TP_API_01.Views.PersonaView;
import jakarta.transaction.Transactional;
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

    @Autowired
    EdificioService edificioService;



    public List<Unidad> ListarUnidades(){
        return unidadRepository.findAll();
    }

    public Unidad buscarUnidad(Integer codigoEdificio, String piso, String numero) throws UnidadException {
        Optional<Unidad> optionalUnidad = unidadRepository.findByEdificioCodigoAndPisoAndNumero(codigoEdificio, piso, numero);

        if (optionalUnidad.isPresent()) {
            return optionalUnidad.get();
        } else {
            throw new UnidadException("La unidad con código de edificio " + codigoEdificio + ", piso " + piso + " y número " + numero + " no existe.");
        }
    }


    public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        List<PersonaView> resultado = new ArrayList<>();
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        List<Persona> duenios = unidad.getDuenios();
        for(Persona persona : duenios)
            resultado.add(persona.toView());
        return resultado;
    }

    public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException{
        List<PersonaView> resultado = new ArrayList<>();
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
        unidad.habitar();
        unidadRepository.save(unidad);
        personaRepository.save(persona);
    }

    public void liberarUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = buscarUnidad(codigo, piso, numero);
        unidad.liberar();
        unidadRepository.save(unidad);
    }



    public Unidad agregarUnidad(int codigo, String piso, String numero) throws EdificioException {
            Unidad unidad = new Unidad();
            unidad.setEdificio(edificioService.buscarEdificio(codigo));
            unidad.setPiso(piso);
            unidad.setNumero(numero);
             return unidadRepository.save(unidad);
    }
    @Transactional
    public void eliminarUnidad(Integer identificador) throws  UnidadException{
        Optional<Unidad> unidad= unidadRepository.findById(identificador);
        unidadRepository.delete(unidad.get());
    }

}
