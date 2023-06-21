package br.com.lucasomac.medvol.domain.patient;

import br.com.lucasomac.medvol.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {
}
