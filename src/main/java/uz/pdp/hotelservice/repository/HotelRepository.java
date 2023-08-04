package uz.pdp.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    public Optional<HotelEntity> findByName(String name);
}
