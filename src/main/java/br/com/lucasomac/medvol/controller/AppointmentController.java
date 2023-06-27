package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.appointment.AppointmentCancelDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDetailsDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentSchedule;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.lucasomac.medvol.commons.Constants.API_APPOINTMENT_PATH;

@RestController
@RequestMapping(API_APPOINTMENT_PATH)
public class AppointmentController {
    private final AppointmentSchedule appointmentSchedule;

    public AppointmentController(AppointmentSchedule appointmentSchedule) {
        this.appointmentSchedule = appointmentSchedule;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailsDTO> toSchedule(@RequestBody @Valid AppointmentDTO data) {
        var appointmentDetails = appointmentSchedule.schedule(data);
        return ResponseEntity.ok(appointmentDetails);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody @Valid AppointmentCancelDTO data) {
        appointmentSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
}