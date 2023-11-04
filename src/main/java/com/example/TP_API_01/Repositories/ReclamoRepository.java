package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Edificio;
import com.example.TP_API_01.Model.Persona;
import com.example.TP_API_01.Model.Reclamo;
import com.example.TP_API_01.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {

    List<Reclamo> findByEdificio(Edificio edificio);
    List<Reclamo> findByPersona(Persona persona);
    List<Reclamo> findByUnidad(Unidad unidad);


    void deleteByPersona(Persona persona);
}
