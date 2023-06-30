package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.Specialty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT d FROM Doctor d
            WHERE
            d.active = true
            AND
            d.specialty = :specialty AND d.id NOT IN (SELECT a.doctor.id FROM Appointment a
                                                      WHERE
                                                      a.date = :date) ORDER BY rand() LIMIT 1
            """)
    Doctor chooseFreeRandomDoctorOnDate(Specialty specialty, LocalDateTime date);

    @Query("""
            SELECT d.active
            FROM Doctor d
            WHERE
            d.id = :id
            """)
    Boolean findActiveById(Long id);
}
