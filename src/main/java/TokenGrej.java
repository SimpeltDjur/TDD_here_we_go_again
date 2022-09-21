import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.awt.font.TextHitInfo;
import java.security.Key;
import java.util.Map;

public class TokenGrej {

    private SomeDB someDB;

    public TokenGrej(SomeDB someDB) {
        this.someDB = someDB;
    }

    public String getToken(String username) {

        Key key = Keys.hmacShaKeyFor("AllWorkAndNoPlayMakesMagnusADullBoy".getBytes());

        String token = Jwts.builder()
                .setSubject(username)
                .addClaims(Map.of("Roll", someDB.getRoll()))
                .signWith(key)
                .compact();

        return token;
    }
}
