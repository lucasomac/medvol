package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;

public interface AppointmentScheduleValidator {
    void validate(AppointmentDTO data);
}
