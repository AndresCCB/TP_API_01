package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.EdificioService;
import com.example.TP_API_01.Exceptions.EdificioException;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Model.Edificio;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Unidad;
import com.example.TP_API_01.Views.EdificioView;
import com.example.TP_API_01.Views.PersonaView;
import com.example.TP_API_01.Views.UnidadView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edificios")
public class EdificioRest {
    @Autowired
    EdificioService edificioService;

    @GetMapping("/listarEdificios")
    public List<EdificioView> listarEdificios(){
        return edificioService.obtenerEdificios();
    }

    @GetMapping("/buscarEdificio/{id}")
    public Edificio buscarEdificio(@PathVariable ("id") int id) throws EdificioException {
        return edificioService.buscarEdificio(id);
    }

    @GetMapping("/buscarUnidadesPorEdificio/{id}")
    public List<UnidadView> buscarUnidadesPorEdificio(@PathVariable("id") int id) throws EdificioException {
        return edificioService.getUnidadesPorEdificio(id);
    }


    @GetMapping("/buscarHabilitadosPorEdificio/{id}")
    public List<PersonaView> buscarHabilitadosPorEdificio(@PathVariable("id") int id) throws EdificioException {
        return edificioService.habilitadosPorEdificio(id);
    }



    @GetMapping("/buscarDueniosPorEdificio/{id}")
    public List<PersonaView> buscarDueniosPorEdificio(@PathVariable("id") int id) throws EdificioException {
        return edificioService.dueniosPorEdificio(id);
    }
    @GetMapping("/buscarHabitantesPorEdificio/{id}")
    public List<PersonaView> buscarHabitantesPorEdificio(@PathVariable("id") int id) throws EdificioException {
        return edificioService.habitantesPorEdificio(id);
    }

    @PostMapping("/agregarEdificio")
    public EdificioView agregarEdificio(@RequestBody Edificio edificio) throws EdificioException {
        edificioService.agregarEdificio(edificio);
        return edificioService.buscarEdificio(edificio.getCodigo()).toView();
    }
    @DeleteMapping("/eliminarEdificio/{id}")
    public void eliminarEdificio(@PathVariable("id") int id) throws  EdificioException{
        edificioService.eliminarEdificio(edificioService.buscarEdificio(id));
    }
}
