package uz.pdp.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import uz.pdp.hotelservice.domain.entity.LocationEntity;
import uz.pdp.hotelservice.exeption.DataNotFoundException;
import uz.pdp.hotelservice.exeption.DuplicateDataException;
import uz.pdp.hotelservice.repository.HotelRepository;
import uz.pdp.hotelservice.repository.LocationRepository;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    @Transactional
    public HotelEntity save(HotelDto hotelDto){

        HotelEntity map = modelMapper.map(hotelDto, HotelEntity.class);
       map.setLocation(hotelDto.getLocation());
       if(hotelRepository.findByName(hotelDto.getName()).isEmpty()){
           return hotelRepository.save(map);

       }
        throw new DuplicateDataException("name already exits");

    }
    public HotelEntity getOneById(UUID id){

        return hotelRepository.findById(id).orElseThrow(()-> new DataNotFoundException("hotel not found"));

    }
    public HotelEntity getOneByName(String name){
        HotelEntity hotel = hotelRepository.findByName(name).orElseThrow(
                ()-> new DataNotFoundException("hotel not found")
        );
        return hotel;
    }
    public HotelEntity updateHotel(HotelDto update, UUID hotelId){
        HotelEntity hotel = hotelRepository.findById(hotelId).orElseThrow(()-> new DataNotFoundException("Hotel not found"));
        HotelEntity newHotel = modelMapper.map(update,HotelEntity.class);
        newHotel.setId(hotel.getId());
        if (newHotel.getName().isEmpty())
            newHotel.setName(hotel.getName());
        if (newHotel.getLocation() == null)
            newHotel.setLocation(hotel.getLocation());
        modelMapper.map(newHotel,hotel);
       return hotelRepository.save(newHotel);

    }
    public void  deleteByName(String name){
        hotelRepository.delete(hotelRepository.findByName(name).orElseThrow(()-> new DataNotFoundException("hotel not found by name")));
    }

}
