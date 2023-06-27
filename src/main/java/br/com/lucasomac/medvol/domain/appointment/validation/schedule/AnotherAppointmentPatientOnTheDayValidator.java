package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentRepository;
import org.springframework.stereotype.Component;

@Component
public class AnotherAppointmentPatientOnTheDayValidator implements AppointmentScheduleValidator {
    private final AppointmentRepository repository;

    public AnotherAppointmentPatientOnTheDayValidator(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(AppointmentDTO data) {
        var firstHour = data.date().withHour(7);
        var lastHour = data.date().withHour(18);
        var hasPatientAnotherAppointmentOnTheDay = repository.existsByPatientIdAndDateBetween(data.idPatient(), firstHour, lastHour);
        if (hasPatientAnotherAppointmentOnTheDay) {
            throw new ValidationException("Patient already has an appointment scheduled on this day!");
        }
    }
}
