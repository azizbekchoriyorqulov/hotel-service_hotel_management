package uz.pdp.hotelservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.hotelservice.domain.entity.HotelEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelRepository extends JpaRepository<HotelEntity, UUID> {
    public Optional<HotelEntity> findByName(String name);
    @Query("SELECT h FROM HotelEntity h WHERE " +
            "6371 * acos(cos(radians(:latitude)) * cos(radians(h.location.latitude)) * " +
            "cos(radians(h.location.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(h.location.latitude))) <= :radius")
    Optional<List<HotelEntity> >findHotelsInRadius(@Param("latitude") Double latitude,
                                         @Param("longitude") Double longitude,
                                         @Param("radius") Double radius);
}
