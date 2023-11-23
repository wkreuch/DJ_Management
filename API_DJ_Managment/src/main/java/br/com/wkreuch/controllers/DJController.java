package br.com.wkreuch.controllers;

import br.com.wkreuch.data.dtos.DJCreateDto;
import br.com.wkreuch.data.dtos.DJResponseDto;
import br.com.wkreuch.models.DJ;
import br.com.wkreuch.services.DJService;
import br.com.wkreuch.utils.mapper.DjMapper;
import jakarta.validation.Valid;
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
    public DJResponseDto create(@RequestBody @Valid DJCreateDto djCreateDto) {
        DJ newDj = DjMapper.convert(djCreateDto);
        return DjMapper.convert(service.create(newDj), true);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DJResponseDto update(@PathVariable(value = "id") Long id, @RequestBody @Valid DJCreateDto djCreateDto) {
        DJ updateDj = DjMapper.convert(djCreateDto);
        return DjMapper.convert(service.update(id, updateDj),true);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<DJResponseDto> findAll() {
        return DjMapper.convert(service.findAll(),true);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DJResponseDto findById(@PathVariable(value = "id") Long id) {
        return DjMapper.convert(service.findById(id),true);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
