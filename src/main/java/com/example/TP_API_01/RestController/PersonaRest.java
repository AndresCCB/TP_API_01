package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.PersonaService;
import com.example.TP_API_01.Exceptions.PersonaException;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Repositories.PersonaRepository;
import com.example.TP_API_01.Repositories.ReclamoRepository;
import com.example.TP_API_01.Views.PersonaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") //permite que se pueda acceder desde un front
@RestController
@RequestMapping("/personas")
public class PersonaRest {
    @Autowired
    PersonaService personaService;


    @GetMapping("/listarPersonas")
    public List<Persona> getPersonas(){
        return personaService.listarPersonas();
    }

    @GetMapping("/buscarPersona/{id}")
    public Persona buscarPersona(@PathVariable("id") String id) throws PersonaException {
        return personaService.buscarPersona(id);
    }

    @DeleteMapping("/eliminarPersona/{id}")
    public void eliminarPersona(@PathVariable("id") String id) throws PersonaException{
        personaService.eliminarPersona(id);
    }
    @PostMapping("/agregarPersona")
    public PersonaView agregarPersona(@RequestBody Persona persona) throws PersonaException {
        personaService.agregarPersona(persona.getDocumento(), persona.getNombre());
        return personaService.buscarPersona(persona.getDocumento()).toView();
    }
    @PutMapping("/registrarUsuario")
    public void registrarUsuario(@RequestParam String documento,@RequestParam String mail, @RequestParam String password) throws PersonaException {
        personaService.RegistroUsuario(documento, mail, password);
    }

    @GetMapping("/validacionIngreso")
    public Boolean validacionIngreso(@RequestParam String mail, @RequestParam String password){
        return personaService.ValidacionIngreso(mail, password);
    }

    @GetMapping("/esAdmin")
    public Boolean esAdmin(@RequestParam String mail, @RequestParam String password){
        return personaService.esAdmin(mail, password);
    }
}
