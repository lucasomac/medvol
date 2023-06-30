package br.com.lucasomac.medvol.domain.appointment.validation.schedule;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentRepository;
import org.springframework.stereotype.Component;

@Component
public class SameTimeAppointmentDoctorValidator implements AppointmentScheduleValidator {
    private final AppointmentRepository repository;

    public SameTimeAppointmentDoctorValidator(AppointmentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(AppointmentDTO data) {
        var doctorHasAppointmentInTheSameHour = repository.existsByDoctorIdAndDateAndCancelReasonIsNull(data.idDoctor(), data.date());
        if (doctorHasAppointmentInTheSameHour) {
            throw new ValidationException("Doctor already has another appointment scheduled at the same time!");
        }
    }
}