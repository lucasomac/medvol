package br.com.lucasomac.medvol.model.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String publicPlace;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Address(AddressDTO data) {
        this.publicPlace = data.publicPlace();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.uf = data.uf();
        this.complement = data.complement();
        this.number = data.number();
    }
}