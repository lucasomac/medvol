package br.com.lucasomac.medvol.domain.address;

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

    public void updateInfo(AddressDTO data) {
        if (data.publicPlace() != null) {
            this.publicPlace = data.publicPlace();
        }
        if (data.neighborhood() != null) {
            this.neighborhood = data.neighborhood();
        }
        if (data.zipcode() != null) {
            this.zipcode = data.zipcode();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
        if (data.city() != null) {
            this.city = data.city();
        }
        if (data.number() != null) {
            this.number = data.number();
        }
        if (data.complement() != null) {
            this.complement = data.complement();
        }
    }
}