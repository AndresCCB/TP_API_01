package com.example.TP_API_01.RestController;

import com.example.TP_API_01.Controllers.ImagenService;
import com.example.TP_API_01.Exceptions.ImagenException;
import com.example.TP_API_01.Model.Imagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imagenes")
public class ImagenRest {

    @Autowired
    ImagenService imagenService;

    @DeleteMapping("/eliminarImagen")
    public void eliminarImagen(@RequestParam Integer numeroImagen) throws ImagenException {
        Imagen imagen= imagenService.buscarImagen(numeroImagen);
        imagenService.eliminarImagen(imagen);
    }
    @GetMapping("/buscarImagen/{id}")
    public Imagen buscarImagen(@PathVariable ("id") Integer numeroImagen) throws ImagenException {
        return imagenService.buscarImagen(numeroImagen);
    }
}
