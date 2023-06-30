package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.Specialty;
import br.com.lucasomac.medvol.domain.address.AddressDTO;
import br.com.lucasomac.medvol.domain.appointment.Appointment;
import br.com.lucasomac.medvol.domain.patient.Patient;
import br.com.lucasomac.medvol.domain.patient.PatientDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("prd")
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return null when the only registered doctor is not available on the date")
    void chooseFreeRandomDoctorOnDateSceneOne() {
        var nextSundayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = createDoctor("Medico", "medico@voll.med", "123456", Specialty.CARDIOLOGY);
        var patient = createPatient("Paciente", "paciente@email.com", "00000000000");
        createAppointment(doctor, patient, nextSundayAt10);

        //when ou act
        var freeDoctor = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGY, nextSundayAt10);

        //then ou assert
        assertThat(freeDoctor).isNull();
    }

    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime nextSundayAt10) {
        em.persist(new Appointment(null, doctor, patient, nextSundayAt10, null));
    }

    private Patient createPatient(String name, String email, String cpf) {
        var patient = new Patient(patientDTO(name, email, cpf));
        em.persist(patient);
        return patient;


    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorDTO(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    @Test
    @DisplayName("Should return doctor when he is available on date")
    void chooseFreeRandomDoctorOnDateSceneTwo() {
        //given ou arrange
        var nextSundayAt10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = createDoctor("Medico", "medico@voll.med", "123456", Specialty.CARDIOLOGY);

        //when ou act
        var freeDoctor = doctorRepository.chooseFreeRandomDoctorOnDate(Specialty.CARDIOLOGY, nextSundayAt10);

        //then ou assert
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private DoctorDTO doctorDTO(String nome, String email, String crm, Specialty specialty) {
        return new DoctorDTO(nome, email, "61999999999", crm, specialty, addressDTO());
    }

    private PatientDTO patientDTO(String nome, String email, String cpf) {
        return new PatientDTO(nome, email, "61999999999", cpf, addressDTO());
    }

    private AddressDTO addressDTO() {
        return new AddressDTO("rua xpto", "bairro", "00000000", "Brasilia", "DF", null, null);
    }
}