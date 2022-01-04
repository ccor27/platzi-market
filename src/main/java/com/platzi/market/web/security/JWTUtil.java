package com.platzi.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

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
}

