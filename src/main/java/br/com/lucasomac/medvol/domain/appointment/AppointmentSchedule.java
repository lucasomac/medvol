package br.com.lucasomac.medvol.domain.appointment;

import br.com.lucasomac.medvol.domain.ValidationException;
import br.com.lucasomac.medvol.domain.appointment.validation.cancel.AppointmentCancelValidator;
import br.com.lucasomac.medvol.domain.appointment.validation.schedule.AppointmentScheduleValidator;
import br.com.lucasomac.medvol.domain.doctor.Doctor;
import br.com.lucasomac.medvol.domain.doctor.DoctorRepository;
import br.com.lucasomac.medvol.domain.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    private final AppointmentRepository appointmentRepository;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;


    private final List<AppointmentScheduleValidator> scheduleValidators;
    private final List<AppointmentCancelValidator> cancelValidators;

    public AppointmentSchedule(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, List<AppointmentScheduleValidator> scheduleValidators, List<AppointmentCancelValidator> cancelValidators) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.scheduleValidators = scheduleValidators;
        this.cancelValidators = cancelValidators;
    }

    public AppointmentDetailsDTO schedule(AppointmentDTO data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient ID do not exists!");
        }
        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Doctor ID do not exists!");
        }
        scheduleValidators.forEach(v -> v.validate(data));

        var patient = patientRepository.findById(data.idPatient()).stream().findFirst().orElse(null);
        var doctor = chooseDoctor(data);
        if (doctor == null) {
            throw new ValidationException("There is no doctor available on this date!");
        }
        var appointment = new Appointment(null, doctor, patient, data.date(), null);
        appointmentRepository.save(appointment);
        return new AppointmentDetailsDTO(appointment);
    }

    public void cancel(AppointmentCancelDTO data) {
        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("The query ID entered does not exist!");
        }

        cancelValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }

    private Doctor chooseDoctor(AppointmentDTO data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null) {
            throw new ValidationException("Specialty is required when doctor is not chose!");
        }
        return doctorRepository.chooseFreeRandomDoctorOnDate(data.specialty(), data.date());
    }
}