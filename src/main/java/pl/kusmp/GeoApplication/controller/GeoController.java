package pl.kusmp.GeoApplication.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.kusmp.GeoApplication.exception.DataException;
import pl.kusmp.GeoApplication.model.Geolocation;
import pl.kusmp.GeoApplication.service.GeoService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GeoController {

    private final GeoService geoService;

    @Autowired
    GeoController(GeoService geoService) {
        this.geoService = geoService;
    }

    @GetMapping("/v1/geo/device/{deviceId}")
    ResponseEntity<List<Geolocation>> getAllDeviceRecordsByDeviceId(@PathVariable Long deviceId) {
        log.info("Getting location by deviceId");
        try {
            var allEntriesByDeviceId = geoService.getAllEntriesByDeviceId(deviceId);
            return new ResponseEntity<>(allEntriesByDeviceId, HttpStatus.OK);
        } catch (DataException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/v1/geo/{id}")
    ResponseEntity<Geolocation> getRecordById(@PathVariable Long id) {
        log.info("Getting single location by id");
        try {
            var geolocation = geoService.getById(id);
            return new ResponseEntity<>(geolocation, HttpStatus.OK);
        } catch (DataException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/v1/geo")
    Geolocation saveNewRecord(@RequestBody @Valid Geolocation geolocation) {
        log.info("Saving new location");
        return geoService.saveNewGeoRecord(geolocation);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
