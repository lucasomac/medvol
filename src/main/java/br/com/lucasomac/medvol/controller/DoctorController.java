package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.model.doctor.Doctor;
import br.com.lucasomac.medvol.model.doctor.DoctorDTO;
import br.com.lucasomac.medvol.model.doctor.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
