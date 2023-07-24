package uz.pdp.hotelservice.domain.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Entity
@Table(name = "location")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HotelEntity extends BaseEntity{
    private String name;
    private String about;

    /**
     * String > room size, Integer amount
     * example:  1 bed: 20
     */
    @ElementCollection // not sure whether works or not
    private Map<String, Integer> size;

    @OneToOne
    private LocationEntity location;

    private int stars;
}
