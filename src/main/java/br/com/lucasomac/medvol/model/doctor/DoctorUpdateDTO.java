package br.com.lucasomac.medvol.model.doctor;

import br.com.lucasomac.medvol.model.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateDTO(@NotNull Long id, String name, String phone, AddressDTO address) {
}