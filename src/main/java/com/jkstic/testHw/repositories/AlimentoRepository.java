package com.jkstic.testHw.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkstic.testHw.models.Alimentos;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimentos, Long>{

	public Optional<Alimentos> findByNombre(String nombre);
}
