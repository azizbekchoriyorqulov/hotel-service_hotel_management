package uz.pdp.hotelservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.domain.entity.LocationEntity;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.exeption.DuplicateDataException;
import uz.pdp.hotelservice.repository.HotelRepository;
import uz.pdp.hotelservice.service.HotelService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
    @InjectMocks
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
        when(modelMapper.map(hotelDto,HotelEntity.class)).thenReturn(hotelEntity);


        // Mock the behavior of the hotelRepository
        when(hotelRepository.findByName(hotelDto.getName())).thenReturn(Optional.of(new HotelEntity()));

        // Act & Assert
        assertThrows(DuplicateDataException.class, () -> hotelService.save(hotelDto));

        // Verify that the hotelRepository.findByName was called once with the correct argument
        verify(hotelRepository, times(1)).findByName(hotelDto.getName());

        // Verify that the hotelRepository.save was not called
        verify(hotelRepository, never()).save(any());
    }
    @Test
    public void testGetOneById_WhenHotelExists_ShouldReturnHotelEntity() {
        // Tayyorlash
        UUID hotelId = UUID.randomUUID();
        HotelEntity expectedHotelEntity = new HotelEntity();
        expectedHotelEntity.setId(hotelId);

        // hotelRepository ning sozlangan baho berish
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(expectedHotelEntity));

        // Amalga oshirish
        HotelEntity actualHotelEntity = hotelService.getOneById(hotelId);

        // Natija bilan solishtirish
        assertEquals(expectedHotelEntity, actualHotelEntity);

        // hotelRepository.findById metodini to'g'ri argument bilan chaqirilganligini tekshirish
        verify(hotelRepository, times(1)).findById(hotelId);
    }
    @Test
    public void testGetOneByIdThrowDataNotFoundException() {
        // Tayyorlash
        UUID hotelId = UUID.randomUUID();

        // hotelRepository ning sozlangan baho berish
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // Amalga oshirish va natija bilan solishtirish
        assertThrows(DataNotFoundException.class, () -> hotelService.getOneById(hotelId));

        // hotelRepository.findById metodini to'g'ri argument bilan chaqirilganligini tekshirish
        verify(hotelRepository, times(1)).findById(hotelId);
    }



    @Test
    public void testUpdateHotel_WhenHotelDoesNotExist_ShouldThrowDataNotFoundException() {
        // Arrange
        UUID hotelId = UUID.randomUUID();
        HotelDto updateDto = new HotelDto();
        updateDto.setName("Updated Hotel");
        updateDto.setLocation(location);

        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DataNotFoundException.class, () -> hotelService.updateHotel(updateDto, hotelId));

        // Verify that the findById method was called once with the correct argument
        verify(hotelRepository, times(1)).findById(hotelId);
        // Verify that the save method was not called since the hotel was not found
        verify(hotelRepository, never()).save(any());
        // Verify that the map method was not called since the hotel was not found
        verify(modelMapper, never()).map(any(), any());
    }
}
