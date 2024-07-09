package cz.filipunk.servicebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.filipunk.servicebook.entity.Auta;

@Repository
public interface AutaRepository extends JpaRepository<Auta, Long> {
	List<Auta> findByUzivatelId(Long userId);
}

