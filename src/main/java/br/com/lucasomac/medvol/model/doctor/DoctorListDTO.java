package br.com.lucasomac.medvol.model.doctor;

import br.com.lucasomac.medvol.model.Specialty;

public record DoctorListDTO(Long id, String name, String email, String crm, Specialty specialty) {
    public DoctorListDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
