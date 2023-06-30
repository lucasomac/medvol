package br.com.lucasomac.medvol.controller;

import br.com.lucasomac.medvol.domain.Specialty;
import br.com.lucasomac.medvol.domain.address.Address;
import br.com.lucasomac.medvol.domain.address.AddressDTO;
import br.com.lucasomac.medvol.domain.doctor.Doctor;
import br.com.lucasomac.medvol.domain.doctor.DoctorDTO;
import br.com.lucasomac.medvol.domain.doctor.DoctorDetailsDTO;
import br.com.lucasomac.medvol.domain.doctor.DoctorRepository;
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

import static br.com.lucasomac.medvol.commons.Constants.API_DOCTORS_PATH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DoctorDTO> doctorDTOJson;

    @Autowired
    private JacksonTester<DoctorDetailsDTO> doctorDetailsDTOJson;

    @MockBean
    private DoctorRepository repository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void createSceneOne() throws Exception {
        var response = mvc.perform(post(API_DOCTORS_PATH)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void createSceneTwo() throws Exception {
        var data = new DoctorDTO("Medico", "medico@voll.med", "61999999999", "123456", Specialty.CARDIOLOGY, addressDTO());

        when(repository.save(any())).thenReturn(new Doctor(data));

        var response = mvc.perform(post("/medicos").contentType(MediaType.APPLICATION_JSON).content(doctorDTOJson.write(data).getJson())).andReturn().getResponse();

        var details = new DoctorDetailsDTO(null, data.name(), data.email(), data.crm(), data.phone(), data.specialty(), new Address(data.address()));
        var jsonEsperado = doctorDetailsDTOJson.write(details).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private AddressDTO addressDTO() {
        return new AddressDTO("rua xpto", "bairro", "00000000", "Brasilia", "DF", null, null);
    }

}
