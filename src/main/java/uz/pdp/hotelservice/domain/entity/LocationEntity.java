package uz.pdp.hotelservice.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationEntity extends BaseEntity{
    private Double latitude;
    private Double longitude;

}
