package com.platzi.market.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    private static String KEY="ccor.1027_2001";

    public String generateToken(UserDetails userDetails){
        //biulder permite crear una secuencia de metodos
        //primero se le envia en usuario, usando el parametro userDetails
        //luego enviar la fecha en la cual se crea el JWT token
        //luego la fecha de expiracion
        //por ultimo el algoritmo a usar y su llave la cual es una constante
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,KEY).compact();
    }

    public boolean validateToken(String token, UserDetails userDetails){
     return extractUsername(token).equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token){
        return  getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //Claims son los objetos que estan dentro del token
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}

