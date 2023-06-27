package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicHoursValidator implements AppointmentScheduleValidator {
    @Override
    public void validate(AppointmentDTO data) {
        var dateOfAppointment = data.date();
        var isSunday = dateOfAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var before = dateOfAppointment.getHour() < 7;
        var after = dateOfAppointment.getHour() > 18;
        if (isSunday || before || after) {
            throw new ValidationException("Consultation outside clinic opening hours!");
        }
    }
}
