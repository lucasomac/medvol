package br.com.lucasomac.medvol.domain.appointment.validation.cancel;

import br.com.lucasomac.medvol.domain.appointment.AppointmentCancelDTO;

public interface AppointmentCancelValidator {
    void validate(AppointmentCancelDTO data);
}
