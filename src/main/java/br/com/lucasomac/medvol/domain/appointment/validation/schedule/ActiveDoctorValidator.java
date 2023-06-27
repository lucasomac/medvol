package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.doctor.DoctorRepository;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctorValidator implements AppointmentScheduleValidator {
    private final DoctorRepository repository;

    public ActiveDoctorValidator(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(AppointmentDTO data) {
        var doctor = data.idDoctor();
        if (doctor == null) {
            return;
        }
        var isDoctorActive = repository.findActiveById(data.idDoctor());
        if (!isDoctorActive) {
            throw new ValidationException("Consultation cannot be scheduled with excluded doctor!");
        }
    }
}