package br.com.lucasomac.medvol.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailsDTO(Long idAppointment, Long idDoctor, Long idPatient, LocalDateTime date){
    public AppointmentDetailsDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
