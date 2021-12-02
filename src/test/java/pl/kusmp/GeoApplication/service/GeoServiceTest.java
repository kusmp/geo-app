package pl.kusmp.GeoApplication.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import pl.kusmp.GeoApplication.exception.DataException;
import pl.kusmp.GeoApplication.model.Geolocation;
import pl.kusmp.GeoApplication.repository.GeoRepository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class GeoServiceTest {

    @Mock
    private GeoRepository geoRepositoryMock;

    @InjectMocks
    private GeoService tested;

    @Test
    public void shouldReturnGeolocationRecordById() throws DataException {
        //given
        Mockito.when(geoRepositoryMock.findById(any())).thenReturn(Optional.of(createGeolocation()));

        //when
        Geolocation geolocation = tested.getById(1L);

        //then
        assertEquals(createGeolocation().getId(), geolocation.getId());
        assertEquals(createGeolocation().getLongitude(), geolocation.getLongitude());
        assertEquals(createGeolocation().getDeviceId(), geolocation.getDeviceId());
        assertEquals(createGeolocation().getLatitude(), geolocation.getLatitude());
        assertEquals(createGeolocation().getLogDate(), geolocation.getLogDate());
    }

    @Test(expected = DataException.class)
    public void shouldThrowDataExceptionWhenIdNotFound() throws DataException {
        //given
        Mockito.when(geoRepositoryMock.findById(any())).thenReturn(Optional.empty());

        //when
        tested.getById(1L);

        //then
    }

    @Test
    public void shouldReturnAllEntriesBasedOnDeviceId() throws DataException {
        //given
        var entries = List.of(createGeolocation(), createGeolocation());
        Mockito.when(geoRepositoryMock.findByDeviceId(any())).thenReturn(entries);

        //when
        var actual = tested.getAllEntriesByDeviceId(2L);

        //then
        assertEquals(2, actual.size());
    }

    @Test(expected = DataException.class)
    public void shouldThrowDataExceptionWhenDeviceIdNotFound() throws DataException {
        //given
        var entries = List.of(createGeolocation(), createGeolocation());
        Mockito.when(geoRepositoryMock.findByDeviceId(any())).thenReturn(Collections.emptyList());

        //when
        tested.getAllEntriesByDeviceId(2L);

        //then
    }

    private Geolocation createGeolocation() {
        var geolocation = new Geolocation();
        geolocation.setId(1L);
        geolocation.setDeviceId(2L);
        geolocation.setLatitude("50");
        geolocation.setLongitude("30");
        geolocation.setLogDate(Date.valueOf("2021-10-21"));

        return geolocation;
    }
}