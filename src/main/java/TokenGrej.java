import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Map;

public class TokenGrej {
    public TokenGrej(SomeDB someDB) {

    }

    public String getToken(String username) {

        Key key = Keys.hmacShaKeyFor("AllWorkAndNoPlayMakesMagnusADullBoy".getBytes());

        String token = Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("Lastname", "Gunnarsson"))
                .signWith(key)
                .compact();

        return token;
    }
}
