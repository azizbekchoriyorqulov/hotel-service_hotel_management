package uz.pdp.hotelservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    public Jws<Claims> extractToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }
    public List<String> getRoles(Collection<?extends GrantedAuthority> roles){
        return roles.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

}
