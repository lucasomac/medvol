package br.com.lucasomac.medvol.model.patient;

import br.com.lucasomac.medvol.model.address.Address;

public record PatientDetailsDTO(Long id, String name, String email, String cpf, String phone, Address address) {

    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
