package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.UnidadService;
import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Exceptions.UnidadException;
import com.example.TP_API_01.Model.Unidad;

import com.example.TP_API_01.Views.PersonaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //permite que se pueda acceder desde un front
@RestController
@RequestMapping("/unidades")
public class UnidadRest {

    @Autowired
    UnidadService unidadService;


    @GetMapping(value = "/buscarUnidad")
    public Unidad buscarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
        return unidadService.buscarUnidad(codigo,piso,numero);
    }

    @GetMapping(value = "/listarUnidades")
    public List<Unidad> listarUnidades(){
       return unidadService.ListarUnidades();
    }
    @GetMapping(value= "/dueniosPorUnidad")
    public List<PersonaView> DueniosPorUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
         return unidadService.dueniosPorUnidad(codigo,piso,numero);
    }
    @GetMapping(value= "/inquilinosPorUnidad")
    public List<PersonaView> InquilinosPorUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
        return unidadService.inquilinosPorUnidad(codigo,piso,numero);
    }

     @PutMapping(value = "/transferirUnidad")
    public void TransferirUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException, UnidadException {
        unidadService.transferirUnidad(codigo,piso,numero,documento);
     }

    @PutMapping(value = "/agregarDuenioUnidad")
    public void AgregarDuenioUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException, UnidadException {
        unidadService.agregarDuenioUnidad(codigo,piso,numero,documento);
    }
    @PutMapping(value = "/alquilarUnidad")
    public void AlquilarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException, UnidadException {
        unidadService.alquilarUnidad(codigo,piso,numero,documento);
    }
    @PutMapping(value = "/agregarInquilinoUnidad")
    public void AgregarInquilinoUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero, @RequestParam String documento) throws PersonaException, UnidadException {
        unidadService.agregarInquilinoUnidad(codigo,piso,numero,documento);
    }

    @PutMapping(value = "/liberarUnidad")
    public void LiberarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws UnidadException {
        unidadService.liberarUnidad(codigo,piso,numero);
    }


    @PostMapping(value = "/agregarUnidad")
    public Unidad agregarUnidad(@RequestParam Integer codigo, @RequestParam String piso, @RequestParam String numero) throws EdificioException {
        return  unidadService.agregarUnidad(codigo,piso,numero);
    }

    //No funciona el delete
    @DeleteMapping(value="/eliminarUnidad")
    public void eliminarUnidad(@RequestParam Integer identificador) throws UnidadException {
        unidadService.eliminarUnidad(identificador);
    }
}
