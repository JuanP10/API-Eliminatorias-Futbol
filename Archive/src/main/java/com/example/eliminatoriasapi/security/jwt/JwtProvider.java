package com.example.eliminatoriasapi.security.jwt;

import java.util.Date;

import com.example.eliminatoriasapi.security.entities.MainUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
    //Logger para mostrar los errores
    //private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    //Clave para verificar el token
    @Value("${app.jwtSecret}")
    private String secret;

    //Tiempo base de expiración
    @Value("${app.jwtExpirationMs}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser userPrincipal = (MainUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    //Creamos una función que permita obtener el nombre de usuario con el token
    public String getUserNameFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    //Creamos una función que permita validar nuestro token con la firma secreta
    //Controlamos cualquier error que pueda existir con el token

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: {} "+ e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: {} "+ e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: {} "+ e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: {} "+ e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: {} "+ e.getMessage());
        }
        return false;
    }
}
