import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.security.Key;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TokenGrejTest {

    TokenGrej tokenGrej;

    @Mock
    SomeDB someDB;

    Key key = Keys.hmacShaKeyFor("AllWorkAndNoPlayMakesMagnusADullBoy".getBytes());

    @BeforeEach
    public void setUp(){
        tokenGrej = new TokenGrej(someDB);

    }

    @ParameterizedTest
    @CsvSource(value = {"anna, STUDENT", "berit, TEACHER", "kalle, ADMIN"})
    public void testTest(String username, String expectedRoll){

        when(someDB.getRoll()).thenReturn(expectedRoll);

        String token = tokenGrej.getToken(username);

        String roll = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("Roll", String.class);

        Assertions.assertEquals(expectedRoll, roll);

    }

    @Test
    public void pestTest(){

        when(someDB.getRoll()).thenReturn(null);

        Assertions.assertThrows(Exception.class, () -> tokenGrej.getToken("username"));

    }


}
