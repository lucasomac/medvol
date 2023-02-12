package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.model.doctor.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("doctors")
@RestController
public class DoctorController {
    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping
    public void create(@Valid @RequestBody DoctorDTO data) {
        this.repository.save(new Doctor(data));
    }

    @GetMapping
    public Page<DoctorListDTO> getAllDoctors(@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable pageable) {
        return repository.findAllByActiveTrue(pageable).map(DoctorListDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfo(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
    }
}
