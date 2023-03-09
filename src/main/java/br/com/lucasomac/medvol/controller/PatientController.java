package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.model.patient.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientController {

    private final PatientRepository repository;

    public PatientController(PatientRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDetailsDTO> create(@RequestBody @Valid PatientDTO data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        this.repository.save(patient);
        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailsDTO(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDetailsDTO> getPatientById(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListDTO>> getAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(repository.findAllByActiveTrue(pageable).map(PatientListDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientDetailsDTO> update(@RequestBody @Valid PatientUpdateDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);

        return ResponseEntity.ok(new PatientDetailsDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }
}
