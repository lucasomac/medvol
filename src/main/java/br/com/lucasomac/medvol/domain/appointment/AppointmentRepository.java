package br.com.lucasomac.medvol.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime lastHour);

    boolean existsByDoctorIdAndDateAndCancelReasonIsNull(Long idDoctor, LocalDateTime date);
}