package br.com.wkreuch.controllers;

import br.com.wkreuch.data.dtos.DJDtoCreate;
import br.com.wkreuch.models.DJ;
import br.com.wkreuch.services.DJService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/dj")
public class DJController {

    @Autowired
    private DJService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DJ create(@RequestBody @Valid DJDtoCreate djDtoCreate) {
        DJ newDj = new DJ();
        BeanUtils.copyProperties(djDtoCreate, newDj);
        return service.create(newDj);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DJ update(@PathVariable(value = "id") Long id, @RequestBody @Valid DJDtoCreate djDtoCreate) {
        DJ updateDj = new DJ();
        BeanUtils.copyProperties(djDtoCreate, updateDj);
        return service.update(id, updateDj);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<DJ> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DJ findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
