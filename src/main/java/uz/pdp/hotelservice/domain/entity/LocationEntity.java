package uz.pdp.hotelservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LocationEntity extends BaseEntity{
    private Double latitude;
    private Double longitude;

}
