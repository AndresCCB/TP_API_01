package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.ReclamoException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.*;
import com.example.TP_API_01.Repositories.ImagenRepository;
import com.example.TP_API_01.Repositories.ReclamoRepository;
import com.example.TP_API_01.Views.Estado;
import com.example.TP_API_01.Views.ReclamoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ReclamoService {

    private final ReclamoRepository reclamoRepository;
    private final ImagenRepository imagenRepository;

    @Autowired
    public ReclamoService(ReclamoRepository reclamoRepository, ImagenRepository imagenRepository) {
        this.reclamoRepository = reclamoRepository;
        this.imagenRepository = imagenRepository;
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

    public Reclamo agregarReclamo(int codigoEdificio, String piso, String numero, String documento, String ubicacion, String descripcion) throws UnidadException, PersonaException, EdificioException {

        Edificio edificio = edificioService.buscarEdificio(codigoEdificio);
        Unidad unidad = unidadService.buscarUnidad(codigoEdificio, piso, numero);
        Persona persona = personaService.buscarPersona(documento);

        Set<Persona> habilitadosEnEdificio = edificio.habilitados();

        if (!habilitadosEnEdificio.contains(persona)) {
            throw new RuntimeException("La persona no vive en el edificio especificado.");
        }
        if(!unidad.getDuenios().contains(persona) && !unidad.getInquilinos().contains(persona)){
            throw new RuntimeException("La persona no vive o no es dueña de la unidad especificada.");
        }

        Reclamo reclamo = new Reclamo(persona, edificio, ubicacion, descripcion, unidad);
        reclamoRepository.save(reclamo);

        return reclamo;
    }

//al agregar una imagen al reclamo duplica la imagen una con el id del reclamo y una sin el
    public void agregarImagenAReclamo(Integer numero,Imagen imagenrecibida) throws ReclamoException {
        Optional<Reclamo> Oreclamo = reclamoRepository.findById(numero);
        if(Oreclamo.isPresent()){
            Reclamo reclamo = Oreclamo.get();

            Imagen imagen = new Imagen();
            imagen.setReclamo(reclamo);
            imagen.setPath(imagenrecibida.getPath());
            imagen.setTipo(imagenrecibida.getTipo());
            reclamo.agregarImagen(imagen);

            reclamoRepository.save(reclamo);

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

    public List<Reclamo> listarReclamos(){
        return reclamoRepository.findAll();
    }
}
