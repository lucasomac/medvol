package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.model.Doctor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("doctors")
@RestController
public class DoctorController {
    @PostMapping
    public void create(@RequestBody Doctor doctor) {
        System.out.println(doctor);
    }

}
