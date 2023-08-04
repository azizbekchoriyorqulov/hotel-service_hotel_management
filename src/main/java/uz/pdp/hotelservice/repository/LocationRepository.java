package uz.pdp.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelservice.domain.entity.LocationEntity;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, UUID> {
    LocationEntity findByLatitude(double Latitude);
}
