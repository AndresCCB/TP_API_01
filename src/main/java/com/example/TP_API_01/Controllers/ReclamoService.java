package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.ReclamoException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.*;
import com.example.TP_API_01.Repositories.ReclamoRepository;
import com.example.TP_API_01.Views.Estado;
import com.example.TP_API_01.Views.ReclamoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamoService {

    private final ReclamoRepository reclamoRepository;

    @Autowired
    public ReclamoService(ReclamoRepository reclamoRepository) {
        this.reclamoRepository = reclamoRepository;
    }

    @Autowired
    UnidadService unidadService;
    @Autowired
    PersonaService personaService;
    @Autowired
    EdificioService edificioService;
    @Autowired
    ImagenService imagenService;
    public List<ReclamoView> reclamosPorEdificio(int codigo){
        Edificio edificio = new Edificio();
        edificio.setCodigo(codigo);
        List<Reclamo> reclamos = reclamoRepository.findByEdificio(edificio);
        List<ReclamoView> resultado = new ArrayList<ReclamoView>();

        for (Reclamo reclamo : reclamos) {
            resultado.add(reclamo.toView());
        }
        return resultado;
    }

    public List<ReclamoView> reclamosPorUnidad(int codigo, String piso, String numero) throws UnidadException {
        Unidad unidad = unidadService.buscarUnidad(codigo,piso,numero);
        List<Reclamo> reclamos = reclamoRepository.findByUnidad(unidad);

        List<ReclamoView> resultado = new ArrayList<ReclamoView>();
        for (Reclamo reclamo : reclamos) {
            resultado.add(reclamo.toView());
        }
        return resultado;
    }


    public List<ReclamoView> reclamosPorPersona(String documento) throws PersonaException {
        Persona persona = personaService.buscarPersona(documento);
        List<Reclamo> reclamos = reclamoRepository.findByPersona(persona);
        List<ReclamoView> resultado = new ArrayList<ReclamoView>();
        for (Reclamo reclamo: reclamos){
            resultado.add(reclamo.toView());
        }
        return resultado;
    }

    public Reclamo agregarReclamo(int codigoedificio, int codigounidad, String piso, String numero, String documento, String ubicacion, String descripcion) throws  UnidadException, PersonaException, EdificioException {
        Edificio edificio = edificioService.buscarEdificio(codigoedificio);
        Unidad unidad = unidadService.buscarUnidad(codigounidad, piso, numero);
        Persona persona = personaService.buscarPersona(documento);
        Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
        reclamoRepository.save(reclamo);
        return reclamo;
    }

    public void agregarImagenAReclamo(Integer numero,Imagen imagen) throws ReclamoException {
        Optional<Reclamo> reclamo = reclamoRepository.findById(numero);
        if(reclamo.isPresent()){
            reclamo.get().agregarImagen(imagen);
            reclamoRepository.save(reclamo.get());
            imagenService.guardarimagen(imagen);
        }else{throw  new ReclamoException("El reclamo no existe");}


    }
    public void cambiarEstado(int numero, Estado estado) throws ReclamoException {
        Reclamo reclamo = reclamoRepository.findById(numero).get();
        if (reclamoRepository.findById(numero).isPresent()){
        reclamo.cambiarEstado(estado);
        reclamoRepository.save(reclamo);
        }else{throw  new ReclamoException("El reclamo con el numero:" + numero + "no existe");}
    }
    public Reclamo buscarReclamo(Integer numero) throws ReclamoException{
        if ( reclamoRepository.findById(numero).isPresent()){
            return reclamoRepository.findById(numero).get();
        }else {
            throw new ReclamoException("El reclamo con el numero:" + numero + "no de encuentra");
        }

    }
    public void eliminarReclamo(Reclamo reclamo){
        reclamoRepository.delete(reclamo);
    }

}
