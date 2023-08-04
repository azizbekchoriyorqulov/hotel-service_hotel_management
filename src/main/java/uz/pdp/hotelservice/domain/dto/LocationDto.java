package uz.pdp.hotelservice.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LocationDto {
    private Double latitude;
    private Double longitude;
}
