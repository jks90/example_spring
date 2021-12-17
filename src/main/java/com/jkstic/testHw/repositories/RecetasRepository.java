package com.jkstic.testHw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkstic.testHw.models.Recetas;

@Repository
public interface RecetasRepository  extends JpaRepository<Recetas, Long>{

}
