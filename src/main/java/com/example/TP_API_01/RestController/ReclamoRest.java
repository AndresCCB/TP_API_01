package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.ReclamoService;
import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.ReclamoException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Imagen;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Views.Estado;
import com.example.TP_API_01.Views.ReclamoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //permite que se pueda acceder desde un front
@RestController
@RequestMapping("/reclamos")
public class ReclamoRest {

    @Autowired
    ReclamoService reclamoService;

    @GetMapping("/buscarReclamosPorEdificio/{id}")
    public List<ReclamoView> reclamosPorEdificio(@PathVariable ("id") int codigoEdificio){
        return reclamoService.reclamosPorEdificio(codigoEdificio);
    }


    @GetMapping("/buscarReclamosPorUnidad")
    public List<ReclamoView> reclamosPorUnidad(@RequestParam Integer codigoEdificio, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
        return reclamoService.reclamosPorUnidad(codigoEdificio,piso,numero);
    }




    @GetMapping("/buscarReclamo/{id}")
    public Reclamo buscarReclamo(@PathVariable ("id") int numero) throws ReclamoException {
        return reclamoService.buscarReclamo(numero);
    }

    @GetMapping("/buscarReclamosPorPersona/{id}")
    public List<ReclamoView> reclamosPorPersona(@PathVariable ("id") String documento) throws PersonaException {
        return reclamoService.reclamosPorPersona(documento);
    }

    @PostMapping("/agregarReclamo")
    public Reclamo agregarReclamo(@RequestParam String documento,@RequestParam Integer codigoEdificio, @RequestParam String ubicacion, @RequestParam String descripcion, @RequestParam String piso, @RequestParam String numeroUnidad) throws PersonaException, UnidadException, EdificioException {
        return reclamoService.agregarReclamo(codigoEdificio,piso,numeroUnidad,documento,ubicacion,descripcion);
    }
    @PutMapping("/agregarImagenaReclamo/{id}")
    public void agregarImagenAReclamo(@PathVariable ("id") Integer id, @RequestBody Imagen imagen) throws ReclamoException {
        reclamoService.agregarImagenAReclamo(id,imagen);
    }
    @PutMapping("/cambiarEstadoReclamo/{id}")
    public void cambiarEstadoReclamo(@PathVariable("id") int id, @RequestParam Estado estado) throws ReclamoException {
        reclamoService.cambiarEstado(id,estado);
    }
    @DeleteMapping("/eliminarReclamo")
    public void eliminarReclamoPorPersona(@RequestParam Integer numero) throws ReclamoException {
        Reclamo reclamo = reclamoService.buscarReclamo(numero);
        reclamoService.eliminarReclamo(reclamo);
    }
}
