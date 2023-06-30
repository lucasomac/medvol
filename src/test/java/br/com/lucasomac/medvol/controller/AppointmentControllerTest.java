package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.Specialty;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentDetailsDTO;
import br.com.lucasomac.medvol.domain.appointment.AppointmentSchedule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static br.com.lucasomac.medvol.commons.Constants.API_APPOINTMENT_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDTO> appointmentDTOJacksonTester;

    @Autowired
    private JacksonTester<AppointmentDetailsDTO> appointmentDetailsDTOJacksonTester;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void scheduleSceneOne() throws Exception {
        var response = mvc.perform(post(API_APPOINTMENT_PATH)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void scheduleSceneTwo() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.CARDIOLOGY;
        var details = new AppointmentDetailsDTO(null, 2L, 5L, date);

        when(appointmentSchedule.schedule(any())).thenReturn(details);

        var response = mvc.perform(post(API_APPOINTMENT_PATH).contentType(MediaType.APPLICATION_JSON).content(appointmentDTOJacksonTester.write(new AppointmentDTO(2L, 5L, date, specialty)).getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var json = appointmentDetailsDTOJacksonTester.write(details).getJson();
        assertThat(response.getContentAsString()).isEqualTo(json);
    }

}