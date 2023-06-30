package br.com.lucasomac.medvol.domain.appointment.validation.cancel;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentCancelDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentRepository;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeCancelValidator implements AppointmentCancelValidator {
    private final AppointmentRepository repository;

    public AdvanceTimeCancelValidator(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(AppointmentCancelDTO data) {
        var appointment = repository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Consultation can only be canceled at least 24 hours in advance!");
        }
    }
}
