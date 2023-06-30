package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.patient.PatientRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentScheduleValidator {
    private final PatientRepository repository;

    public ActivePatientValidator(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(AppointmentDTO data) {
        var isPatientActive = repository.findActiveById(data.idPatient());
        if (!isPatientActive) {
            throw new ValidationException("Consultation cannot be scheduled with excluded patient!");
        }
    }
}