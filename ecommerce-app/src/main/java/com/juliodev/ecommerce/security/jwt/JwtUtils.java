package com.juliodev.ecommerce.security.jwt;

import com.juliodev.ecommerce.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtExpirationMs}")
    private long jwtExpirationTimeInMs;

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtCookieName}")
    private String jwtCookieName;

    public String getJwtFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) return bearerToken.substring(7);
        return null;
    }

    public String getJwtFromCookies(HttpServletRequest request){
        Cookie cookie = WebUtils.getCookie(request,jwtCookieName);
        if(cookie != null) return cookie.getValue();
        else return null;
    }

    public ResponseCookie generateJwtCookie(UserDetailsImpl userDetails){
        String jwt = generateTokenFromUsername(userDetails.getUsername());
        return ResponseCookie.from(jwtCookieName, jwt)
                .path("/api")
                .maxAge(24 * 60 * 60)
                .httpOnly(false)
                .build();
    }

    public ResponseCookie deleteJwtCookie(){
        return ResponseCookie.from(jwtCookieName, null)
                .path("/api")
                .build();
    }

    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationTimeInMs))
                .signWith(key())
                .compact();
    }

    public String getUserNameFromJWTToken(String token){
        return Jwts.parser().verifyWith((SecretKey) key()).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    public Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public boolean validateJwtToken(String authToken){
        try{
            Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(authToken);
            return true;
        }
        catch(MalformedJwtException ex){
            logger.error("Invalid JWT token: {}", ex.getMessage());
        }
        catch(ExpiredJwtException ex){
            logger.error("JWT token is expired: {}", ex.getMessage());
        }
        catch(UnsupportedJwtException ex){
            logger.error("JWT token is unsupported: {}", ex.getMessage());
        }
        catch(IllegalArgumentException ex){
            logger.error("JWT claims string is empty: {}", ex.getMessage());
        }

        return false;
    }
}
