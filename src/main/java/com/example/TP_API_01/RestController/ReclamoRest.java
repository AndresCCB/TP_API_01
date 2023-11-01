package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.ReclamoService;
import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.ReclamoException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Imagen;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Views.Estado;
import com.example.TP_API_01.Views.ReclamoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reclamos")
public class ReclamoRest {

    @Autowired
    ReclamoService reclamoService;

    @GetMapping("/buscarReclamosPorEdificio/{id}")
    public List<ReclamoView> reclamosPorEdificio(@PathVariable ("id") int id){
        return reclamoService.reclamosPorEdificio(id);
    }


    @GetMapping("/buscarReclamosPorUnidad")
    public List<ReclamoView> reclamosPorUnidad(@RequestParam Integer codigoEdificio, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
        return reclamoService.reclamosPorUnidad(codigoEdificio,piso,numero);
    }




    @GetMapping("/buscarReclamos/{id}")
    public ReclamoView buscarReclamos(@PathVariable ("id") int id) throws ReclamoException {
        return reclamoService.reclamosPorNumero(id);
    }

    @GetMapping("/buscarReclamosPorPersona/{id}")
    public List<ReclamoView> reclamosPorPersona(@PathVariable ("id") String id) throws PersonaException {
        return reclamoService.reclamosPorPersona(id);
    }

    @PostMapping("/agregarReclamo")
    public Reclamo agregarReclamo(@RequestParam String documento,@RequestParam Integer codigo, @RequestParam String ubicacion, @RequestParam String descripcion, @RequestParam Integer identificador, @RequestParam String piso, @RequestParam String numeroUnidad) throws PersonaException, UnidadException, EdificioException {
        return reclamoService.agregarReclamo(codigo,identificador,piso,numeroUnidad,documento,ubicacion,descripcion);
    }
    @PostMapping("/agregarImagenaReclamo/{id}")
    public void agregarImagenAReclamo(@RequestBody Imagen imagen) throws ReclamoException {
        reclamoService.agregarImagenAReclamo(imagen.getNumero(), imagen.getPath(),imagen.getTipo());
    }
    @PutMapping("/cambiarEstadoReclamo/{id}")
    public void cambiarEstadoReclamo(@PathVariable("id") int id, @RequestParam("estado")Estado estado) throws ReclamoException {
        reclamoService.cambiarEstado(id,estado);
    }
}
