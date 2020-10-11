package com.app.Message_Backend.auth;

import com.app.Message_Backend.entities.User;
import graphql.GraphQLException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
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

    public UserDetailsImp validate(String jwt) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();


            return new UserDetailsImp(body.getSubject(), "", jwt);
        } catch(GraphQLException error) {
            return new UserDetailsImp();
        }
    }

    public String getToken(HttpServletRequest request) {
        String tokenFromQueryOrMutation = request.getHeader("Authorization");

        if(tokenFromQueryOrMutation != null && tokenFromQueryOrMutation.startsWith(("Bearer "))) {
            return tokenFromQueryOrMutation.replace("Bearer ", "");
        }

        String tokenFromSubsctription = request.getParameter("authToken");
        System.out.println(tokenFromSubsctription);
        if(tokenFromSubsctription != null) return tokenFromSubsctription;

        return null;
    }
}
