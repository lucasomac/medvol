package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.Specialty;
import br.com.lucasomac.medvol.domain.address.Address;

public record DoctorDetailsDTO(Long id, String name, String email, String phone, String crm, Specialty specialty,
                               Address address) {
    public DoctorDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
