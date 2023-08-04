package uz.pdp.hotelservice.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelEntity extends BaseEntity{
    private String name;
    private String about;

    @ElementCollection // not sure whether works or not
    private Map<String, Integer> size;

    @OneToOne(cascade = CascadeType.ALL)
    private LocationEntity location;

    private int stars;
}
