package com.example.TP_API_01.Controllers;

import com.example.TP_API_01.Model.Imagen;
import com.example.TP_API_01.Repositories.ImagenRepository;
import com.example.TP_API_01.Views.ImagenView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    @Autowired
    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }


    public void guardarimagen(Imagen imagen){
        imagenRepository.save(imagen);
    }
}
