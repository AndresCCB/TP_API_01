package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.UnidadService;
import com.example.TP_API_01.Model.Unidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadRest {

    @Autowired
    UnidadService unidadService;
    /*
    @GetMapping("/buscarUnidad")
    public Unidad buscarUnidad(){
        unidadService.buscarUnidad()
    }

     */
}
