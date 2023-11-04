package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UnidadRepository extends JpaRepository<Unidad, Integer> {
    Optional<Unidad> findByEdificioCodigoAndPisoAndNumero(int codigoEdificio, String piso, String numero);


}
