package br.com.wkreuch.services;

import br.com.wkreuch.exceptions.ErrorCode;
import br.com.wkreuch.exceptions.RequiredObjectIsNullException;
import br.com.wkreuch.exceptions.ResourceNotFoundException;
import br.com.wkreuch.models.DJ;
import br.com.wkreuch.repositories.DJRepository;
import br.com.wkreuch.utils.mapper.DjMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DJService {

    @Autowired
    private DJRepository djRepository;

    @Transactional
    public DJ create(DJ dj) {
        if (dj == null) throw new RequiredObjectIsNullException();
        return djRepository.save(dj);
    }

    public List<DJ> findAll() {
        return djRepository.findAll();
    }

    public DJ findById(Long id) {
        return djRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));
    }

    @Transactional
    public DJ update(Long id, DJ dj) {
        if (dj == null) throw new RequiredObjectIsNullException();

        DJ djPersisted = djRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));

        DjMapper.copyProperties(dj, djPersisted, "idDj");

        djPersisted = djRepository.save(djPersisted);
        return djPersisted;
    }

    @Transactional
    public void delete(Long id) {
        DJ djPersisted = djRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ErrorCode.NOT_FOUND.getMessage()));
        djRepository.delete(djPersisted);
    }
}
