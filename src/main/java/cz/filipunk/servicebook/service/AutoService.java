package cz.filipunk.servicebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.filipunk.servicebook.entity.Auta;
import cz.filipunk.servicebook.repository.AutaRepository;

@Service
public class AutoService {

    @Autowired
    private AutaRepository autorepository;

	
    public List<Auta> findAll() {
        return autorepository.findAll();
    }

    public void save(Auta auto) {
    	autorepository.save(auto);
    }
    

    public List<Auta> findByUzivatelId(Long userId) {
        return autorepository.findByUzivatelId(userId);
    }
}
