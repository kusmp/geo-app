package pl.kusmp.GeoApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kusmp.GeoApplication.model.Geolocation;

import java.util.List;

@Repository
public interface GeoRepository extends JpaRepository<Geolocation, Long> {

     List<Geolocation>  findByDeviceId(Long deviceId);
}
