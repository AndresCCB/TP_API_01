package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Administrador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Administrador findByDocumento_Documento(String documento);
}
