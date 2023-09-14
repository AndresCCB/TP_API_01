package com.example.TP_API_01.Repositories;

import com.example.TP_API_01.Model.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EdificioRepository extends JpaRepository<Edificio, Integer> {
}
