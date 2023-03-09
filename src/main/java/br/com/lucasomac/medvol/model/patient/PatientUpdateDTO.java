package br.com.lucasomac.medvol.model.patient;

import br.com.lucasomac.medvol.model.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
