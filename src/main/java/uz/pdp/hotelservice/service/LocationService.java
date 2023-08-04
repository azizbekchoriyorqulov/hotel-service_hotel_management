package uz.pdp.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.hotelservice.domain.dto.LocationDto;
import uz.pdp.hotelservice.domain.entity.LocationEntity;
import uz.pdp.hotelservice.repository.LocationRepository;

@RequiredArgsConstructor
@Service
public class LocationService {
    private final ModelMapper modelMapper;
    private final LocationRepository locationRepository;
    public LocationEntity save(LocationDto locationDto){
        LocationEntity map = modelMapper.map(locationDto, LocationEntity.class);
return locationRepository.save(map);
    }
}
