package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Administrador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, String> {
    Optional<Administrador> findByDocumento(String documento);
}
