package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements AppointmentScheduleValidator {
    @Override
    public void validate(AppointmentDTO data) {
        var dateOfAppointment = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dateOfAppointment).toMinutes();
        if (differenceInMinutes < 30) {
            throw new ValidationException("Consultation must be scheduled at least 30 minutes in advance!");
        }
    }
}
