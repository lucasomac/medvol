package br.com.lucasomac.medvol.domain.doctor;

import br.com.lucasomac.medvol.domain.Specialty;
import br.com.lucasomac.medvol.domain.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDTO(@NotBlank String name, @NotBlank @Email String email, @NotBlank String phone,
                        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm, @NotNull Specialty specialty,
                        @NotNull @Valid AddressDTO address) {
}