package cz.filipunk.servicebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.filipunk.servicebook.entity.Auta;
import cz.filipunk.servicebook.entity.Opravy;

@Repository
public interface OpravyRepository extends JpaRepository<Opravy, Long> {
	List<Opravy> findByCarId(Long carId);
	List<Opravy> findByUzivatelId(Long userId);
}
