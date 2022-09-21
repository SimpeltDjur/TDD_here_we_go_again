import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Base64;
import java.util.HashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserGrejTest {

    UserGrej userGrej;

    @Mock
    SomeDB someDB;

    HashMap<String, String> map;

    @BeforeEach
    public void setUp(){
        userGrej = new UserGrej(someDB);
        map = new HashMap<>();
        map.putIfAbsent("anna", "losen");
        map.putIfAbsent("berit", "123456");
        map.putIfAbsent("kalle", "password");
    }

//    @Test
//    public void inloggTest_should_be_true(){
//
//        when(someDB.getUsers()).thenReturn(map);
//
//        Assertions.assertTrue(userGrej.loggIn("anna", "losen"));
//        Assertions.assertTrue(userGrej.loggIn("berit", "123456"));
//        Assertions.assertTrue(userGrej.loggIn("kalle", "password"));
//
//    }
//    @Test
//    public void inloggTest_should_be_false(){
//
//        when(someDB.getUsers()).thenReturn(map);
//        ;
//        Assertions.assertFalse(userGrej.loggIn("bob", "password"));
//        Assertions.assertFalse(userGrej.loggIn("anna", "password"));
//        Assertions.assertFalse(userGrej.loggIn("cecar", "sallad"));
//
//    }

    @ParameterizedTest
    @CsvSource(value = {"anna, losen", "berit, 123456"})
    public void inlogg_With_Tooken(String userName, String passWord) throws Exception {

        byte[] originalAsBytes = userName.getBytes();
        byte[] originalAsBase64 = Base64.getEncoder().encode(originalAsBytes);
        String expected = new String(originalAsBase64); // helt onödigt alias men det gör det lättare för mig att komma ihåg vad som är vad.

        when(someDB.getUsers()).thenReturn(map);

        var token = userGrej.loggIn(userName, passWord);
        Assertions.assertEquals(expected, token);


    }
    @ParameterizedTest
    @CsvSource(value = {"anna, LösenFel", "NamnFel, 123456", "Pelle, AlltFel", "anna, 123456"})
    public void inlogg_That_Throws(String userName, String passWord){

        when(someDB.getUsers()).thenReturn(map);

        Assertions.assertThrows(Exception.class, () -> userGrej.loggIn(userName, passWord));


    }



}
