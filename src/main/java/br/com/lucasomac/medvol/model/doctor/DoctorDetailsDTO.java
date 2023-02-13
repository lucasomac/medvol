package br.com.lucasomac.medvol.model.doctor;

import br.com.lucasomac.medvol.model.Specialty;
import br.com.lucasomac.medvol.model.address.Address;

public record DoctorDetailsDTO(Long id, String name, String email, String phone, String crm, Specialty specialty,
                               Address address) {
    public DoctorDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
