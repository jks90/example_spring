package com.jkstic.testHw.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jkstic.testHw.models.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Long>{

	Optional<Dia> findByFecha(Date fecha);
	Optional<List<Dia>> findByFechaBetween(Date from,Date to);
}
