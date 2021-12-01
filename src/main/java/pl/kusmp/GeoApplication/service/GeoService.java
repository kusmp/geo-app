package pl.kusmp.GeoApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kusmp.GeoApplication.exception.DataException;
import pl.kusmp.GeoApplication.model.Geolocation;
import pl.kusmp.GeoApplication.repository.GeoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GeoService {

    private final GeoRepository geoRepository;

    @Autowired
    GeoService(GeoRepository geoRepository){
        this.geoRepository = geoRepository;
    }

    public Geolocation getById(Long id) throws DataException {
        Optional<Geolocation> geolocation = geoRepository.findById(id);
        if(geolocation.isEmpty()){
            throw new DataException("Id is not present.");
        }
        return geolocation.get();
    }

    public List<Geolocation> getAllEntriesByDeviceId(Long deviceId) throws DataException {
        List<Geolocation> geolocations = geoRepository.findByDeviceId(deviceId);
        if(CollectionUtils.isEmpty(geolocations)){
            throw new DataException("Device not found.");
        }
        return geolocations;
    }

    public Geolocation saveNewGeoRecord(Geolocation geolocation){
        return geoRepository.save(geolocation);
    }

}
