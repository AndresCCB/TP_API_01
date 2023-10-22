package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, String> {
    Persona findByMailAndContrasenia(String mail, String contrasenia);

}
