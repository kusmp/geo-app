package pl.kusmp.GeoApplication.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@Entity
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Device ID is mandatory")
    private Long deviceId;
    @NotNull(message = "Log Date is mandatory")
    private Date logDate;
    @NotBlank(message = "Latitude is mandatory")
    private String latitude;
    @NotBlank(message = "Longitude is mandatory")
    private String longitude;
}
