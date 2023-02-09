package br.com.lucasomac.medvol.model;

public record Doctor(String name, String email, String crm, Specialty specialty, Address address) {
}