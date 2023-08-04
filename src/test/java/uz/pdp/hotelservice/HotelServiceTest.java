package uz.pdp.hotelservice;

import ch.qos.logback.core.model.processor.conditional.ThenModelHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.domain.entity.LocationEntity;
import uz.pdp.hotelservice.exeption.DuplicateDataException;
import uz.pdp.hotelservice.repository.HotelRepository;
import uz.pdp.hotelservice.service.HotelService;

import java.util.Map;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


public class HotelServiceTest {
    private final String name = "test";
    private final String about = "test";

    private final LocationEntity location = LocationEntity.builder().latitude(123.0).longitude(1134.0).build();
    private final Map<String,Integer> size = Map.of("test",203);
    private final int stars = 0;
    @Mock
  private   HotelRepository hotelRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private HotelService hotelService;
    HotelEntity hotelEntity ;

    HotelDto hotelDto;
    @BeforeEach
    void setUpHotelDto(){

        hotelDto = HotelDto.builder().about(about)
                .stars(stars)
                .name(name)
                .location(location)
                .size(size).build();
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hotelEntity = HotelEntity.builder()
                .about(about)
                .name(name)
                .location(location)
                .size(size)
                .stars(stars)
                .build();
    }

    @Test
    void saveHotel(){
        when(modelMapper.map(hotelDto,HotelEntity.class)).thenReturn(hotelEntity);
        when(hotelRepository.save(hotelEntity)).thenReturn(hotelEntity);
        HotelEntity hotel = hotelRepository.save(hotelEntity);
        assertEquals(name,hotel.getName());
    }
    @Test
    void hotelExistsByNameTest() {
        hotelService.save(hotelDto);
        Optional<HotelEntity> optionalHotel = hotelRepository.findByName("test");
        assertThrows(DuplicateDataException.class,()->hotelService.save(hotelDto));
    }  
}
