package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Exceptions.ImagenException;
import com.example.TP_API_01.Model.Imagen;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Repositories.ImagenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;


    @Autowired
    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }


    public Imagen guardarimagen(String path, String tipo, Reclamo numeroReclamo){
        Imagen imagen = new Imagen(path,tipo,numeroReclamo);
        return imagenRepository.save(imagen);

    }
    //no funciona el delete de imagen
    public void eliminarImagen(Imagen imagen){
        imagenRepository.delete(imagen);
    }
    public Imagen buscarImagen(Integer numeroImagen) throws ImagenException {
        Optional<Imagen> imagen= imagenRepository.findById(numeroImagen);
        if (imagen.isPresent()){
            return imagen.get();
        }else{throw new ImagenException("La imagen no existe");}
    }
}
