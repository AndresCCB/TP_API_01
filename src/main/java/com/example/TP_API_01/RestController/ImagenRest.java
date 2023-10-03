package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.ImagenService;
import com.example.TP_API_01.Model.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenRest {

    @Autowired
    ImagenService imagenService;

    @PostMapping("/agregarImagen")
    public Imagen agregarImagen(@RequestBody Imagen imagen){
        return imagenService.guardarimagen(imagen);
    }
}
