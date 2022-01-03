package com.jkstic.testHw.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkstic.testHw.models.Recetas;

@Repository
public interface RecetasRepository  extends JpaRepository<Recetas, Long>{

	public Optional<Recetas> findByNombre(String nombre);
}
