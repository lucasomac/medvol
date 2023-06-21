package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.doctor.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.lucasomac.medvol.commons.Constants.API_DOCTORS_PATH;

@RequestMapping(API_DOCTORS_PATH)
@RestController
public class DoctorController {
    private final DoctorRepository repository;

    public DoctorController(DoctorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<DoctorDetailsDTO> create(@Valid @RequestBody DoctorDTO data, UriComponentsBuilder uriComponentsBuilder) {
        var doctor = new Doctor(data);
        this.repository.save(doctor);
        var uri = uriComponentsBuilder.path(API_DOCTORS_PATH + "/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailsDTO(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailsDTO> getDoctorById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListDTO>> getAllDoctors(@PageableDefault(size = 4, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(pageable).map(DoctorListDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailsDTO> update(@RequestBody @Valid DoctorUpdateDTO data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfo(data);
        return ResponseEntity.ok(new DoctorDetailsDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }
}
