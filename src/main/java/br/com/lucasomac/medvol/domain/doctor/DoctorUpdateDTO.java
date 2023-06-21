package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO(@NotNull Long id, String name, String phone, AddressDTO address) {
}