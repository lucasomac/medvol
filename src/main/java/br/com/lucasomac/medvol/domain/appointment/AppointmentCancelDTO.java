package br.com.lucasomac.medvol.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancelDTO(@NotNull Long idAppointment,

                                   @NotNull CancelReason reason) {
}
