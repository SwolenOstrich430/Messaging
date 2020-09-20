package com.app.Message_Backend.auth;

import com.app.Message_Backend.entities.User;
import graphql.GraphQLException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {
    // TODO: throw this in properties
    private final String secret = "0pTosXQqZc0b1oy3UHBJ";

    public String build(User user) {
        Claims claims  = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("email", String.valueOf(user.getEmail()));
        claims.put("roles", String.valueOf(user.getRoles()));
        claims.put("id", user.getId());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Long validate(String jwt) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();

            Number temp = (Integer) body.get("id");
            Long userId = temp.longValue();
            return userId;
        } catch(GraphQLException error) {
            return null;
        }
    }

    public String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith(("Bearer "))) {
            return header.replace("Bearer ", "");
        } else {
            System.out.println("returning null");
            return null;
        }
    }
}
