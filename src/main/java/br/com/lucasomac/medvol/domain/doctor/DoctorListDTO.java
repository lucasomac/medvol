package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.Specialty;

public record DoctorListDTO(Long id, String name, String email, String crm, Specialty specialty) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
