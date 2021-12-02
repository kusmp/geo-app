package pl.kusmp.GeoApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.kusmp.GeoApplication.model.Geolocation;
import pl.kusmp.GeoApplication.service.GeoService;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GeoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GeoService geoService;

    @Test
    void shouldSaveNewEntityWhenValidParametersProvided() throws Exception {
        var formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        var geolocation = new Geolocation();
        geolocation.setDeviceId(120L);
        geolocation.setLatitude("50");
        geolocation.setLongitude("30");
        geolocation.setLogDate(formatter.parse("2021-02-12"));
        this.mockMvc.perform(post("/v1/geo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                        .content(new ObjectMapper().writeValueAsString(geolocation)))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnBadRequestStatusWhenMissingParameter() throws Exception {
        var formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        var geolocation = new Geolocation();
        geolocation.setLatitude("50");
        geolocation.setLongitude("30");
        geolocation.setLogDate(formatter.parse("2021-02-12"));
        this.mockMvc.perform(post("/v1/geo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
                        .content(new ObjectMapper().writeValueAsString(geolocation)))
                .andExpect(status().isBadRequest());

    }
}