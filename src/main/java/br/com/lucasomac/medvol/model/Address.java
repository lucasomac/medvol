package br.com.lucasomac.medvol.model;

public record Address(String publicPlace, String neighborhood, String zipcode, String city, String uf,
                      String complement, String number) {
}