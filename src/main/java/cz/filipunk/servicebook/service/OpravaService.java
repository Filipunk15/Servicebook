package cz.filipunk.servicebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.filipunk.servicebook.entity.Auta;
import cz.filipunk.servicebook.entity.Opravy;
import cz.filipunk.servicebook.repository.OpravyRepository;

@Service
public class OpravaService {

    @Autowired
    private OpravyRepository OpravyRepository;

	
    public List<Opravy> findAll() {
        return OpravyRepository.findAll();
    }

    public void save(Opravy auto) {
    	OpravyRepository.save(auto);
    }
    public List<Opravy> findByCarId(Long carId) {
        return OpravyRepository.findByCarId(carId);
    }
    public List<Opravy> findByUzivatelId(Long userId) {
        return OpravyRepository.findByUzivatelId(userId);
    }
    public void deleteById(Long id){
    	OpravyRepository.deleteById(id);
    }
}
