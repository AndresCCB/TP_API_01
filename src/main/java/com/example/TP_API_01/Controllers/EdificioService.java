package com.example.TP_API_01.Controllers;


import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Model.Edificio;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Repositories.EdificioRepository;
import com.example.TP_API_01.Views.EdificioView;
import com.example.TP_API_01.Views.PersonaView;
import com.example.TP_API_01.Views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EdificioService {

    private final EdificioRepository edificioRepository;

    @Autowired
    public EdificioService(EdificioRepository edificioRepository){
        this.edificioRepository = edificioRepository;
    }

    public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException {
        Edificio edificio = buscarEdificio(codigo);
        List<Unidad> unidades = edificio.getUnidades();
        List<UnidadView> resultado = new ArrayList<>();

        for (Unidad unidad : unidades) {
            resultado.add(unidad.toView());
        }

        return resultado;
    }

    public Edificio buscarEdificio(int codigo) throws EdificioException {
        Optional<Edificio> optionalEdificio = edificioRepository.findById(codigo);

        if (optionalEdificio.isPresent()) {
            return optionalEdificio.get();
        } else {
            throw new EdificioException("El edificio con el código " + codigo + " no existe.");
        }
    }

    public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException{
        List<PersonaView> resultado = new ArrayList<PersonaView>();
        Edificio edificio = buscarEdificio(codigo);
        Set<Persona> habilitados = edificio.habilitados();
        for(Persona persona : habilitados)
            resultado.add(persona.toView());
        return resultado;
    }
    public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException{
        List<PersonaView> resultado = new ArrayList<PersonaView>();
        Edificio edificio = buscarEdificio(codigo);
        Set<Persona> duenios = edificio.duenios();
        for(Persona persona : duenios)
            resultado.add(persona.toView());
        return resultado;
    }



    public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException{
        List<PersonaView> resultado = new ArrayList<PersonaView>();
        Edificio edificio = buscarEdificio(codigo);
        Set<Persona> habitantes = edificio.habitantes();
        for(Persona persona : habitantes)
            resultado.add(persona.toView());
        return resultado;
    }


    public List<EdificioView> obtenerEdificios(){
        List<Edificio> edificios = edificioRepository.findAll();
        List<EdificioView> resultado = new ArrayList<>();
        for (Edificio edificio: edificios){
            resultado.add(edificio.toView());
        }
        return resultado;
    }

    public Edificio agregarEdificio(Edificio edificio){
        return edificioRepository.save(edificio);
    }
    public void eliminarEdificio(Edificio edificio){
        edificioRepository.delete(edificio);
    }

}
