package uz.pdp.hotelservice.domain.dto;

import lombok.*;
import uz.pdp.hotelservice.domain.entity.LocationEntity;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HotelDto {
    private LocationEntity location;
    private String name;
    private String about;
    private Map<String, Integer> size;

    private int stars;
}
