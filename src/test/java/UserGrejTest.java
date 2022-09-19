import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.mockito.Mockito.when;

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

    @ExtendWith(MockitoExtension.class)
    public void inloggTest(){

        when(someDB.getUsers()).thenReturn(map);

        Assertions.assertTrue(userGrej.loggIn("anna", "losen"));
        Assertions.assertTrue(userGrej.loggIn("berit", "123456"));
        Assertions.assertTrue(userGrej.loggIn("kalle", "password"));
        Assertions.assertFalse(userGrej.loggIn("bob", "password"));
        Assertions.assertFalse(userGrej.loggIn("anna", "password"));
        Assertions.assertFalse(userGrej.loggIn("cecar", "sallad"));

    }
}
