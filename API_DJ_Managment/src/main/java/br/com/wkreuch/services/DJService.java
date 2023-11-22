package br.com.wkreuch.services;

import br.com.wkreuch.models.DJ;
import br.com.wkreuch.repositories.DJRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DJService {

    @Autowired
    private DJRepository repository;

    @Transactional
    public DJ create(DJ dj) {
        return repository.save(dj);
    }

    public List<DJ> findAll() {
        return repository.findAll();
    }

    public DJ findById(Long id) {
        DJ dj = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found Dj with this ID"));
        return dj;
    }

    @Transactional
    public DJ update(Long id, DJ dj) {
        DJ djPersisted = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found Dj with this ID"));
        djPersisted.setFirstName(dj.getFirstName());
        djPersisted.setLastName(dj.getLastName());
        djPersisted.setCountryIdRegistration(dj.getCountryIdRegistration());
        djPersisted.setBirthDate(dj.getBirthDate());
        djPersisted.setArtistName(dj.getArtistName());

        djPersisted = repository.save(djPersisted);
        return djPersisted;
    }

    @Transactional
    public void delete(Long id) {
        DJ djPersisted = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found Dj with this ID"));
        repository.delete(djPersisted);
    }
}
