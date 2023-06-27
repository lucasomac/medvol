package br.com.lucasomac.medvol.domain.appointment;

import br.com.lucasomac.medvol.domain.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO(Long idDoctor, @NotNull Long idPatient, @NotNull @Future @JsonFormat(pattern = "dd/MM/yyyy HH:mm") LocalDateTime date,
                             Specialty specialty) {
}