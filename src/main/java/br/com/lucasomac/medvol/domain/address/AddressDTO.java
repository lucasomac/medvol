package br.com.lucasomac.medvol.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(@NotBlank String publicPlace, @NotBlank String neighborhood,
                         @NotBlank @Pattern(regexp = "\\d{8}") String zipcode, @NotBlank String city,
                         @NotBlank String uf, String complement, String number) {
}