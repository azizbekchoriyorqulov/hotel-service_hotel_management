package uz.pdp.hotelservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelservice.domain.dto.HotelDto;
import uz.pdp.hotelservice.domain.entity.HotelEntity;
import uz.pdp.hotelservice.service.HotelService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequestMapping("hotel/api/v1")
@RequiredArgsConstructor
@RestController
public class HotelController {
    private final HotelService hotelService;

    @PostMapping("/add")
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public ResponseEntity addHotel(
            @RequestBody HotelDto hotelDto
    ) {
        return ResponseEntity.ok(hotelService.save(hotelDto));
    }

    @GetMapping("/{id}/getOne")
    @PreAuthorize(value = "hasanyRole('SUPER_ADMIN','ADMIN','MANAGER')")
    public ResponseEntity getOne(
            @PathVariable UUID id
    ) {
        HotelEntity hotel = hotelService.getOneById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("name", hotel.getName());
        response.put("location", hotel.getLocation());
        response.put("about", hotel.getAbout());
        response.put("size", hotel.getSize());
        response.put("stars", hotel.getStars());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update")

    @PreAuthorize( value = "hasAnyRole('SUPER_ADMIN','MANAGER')")
    public ResponseEntity updateHotel(
            @PathVariable UUID id,
            @RequestBody HotelDto hotelDto
    ) {
        HotelEntity hotel = hotelService.updateHotel(hotelDto, id);
        return ResponseEntity.ok(hotel);
    }
    @GetMapping("/{name}/deleteByName")
    public String deleteHotel(
            @PathVariable String name
    ){
        hotelService.deleteByName(name);
        return "hotel delete";
    }
}
