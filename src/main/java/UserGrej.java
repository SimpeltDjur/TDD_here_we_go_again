import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

public class UserGrej {

    SomeDB someDB;
    public UserGrej(SomeDB someDB) {
        this.someDB = someDB;
    }

    public String loggIn(String userName, String passWord) throws Exception {

        HashMap<String, String> userMap = someDB.getUsers();

        byte[] originalAsBytes = userName.getBytes();
        byte[] originalAsBase64 = Base64.getEncoder().encode(originalAsBytes);
        String byte64String = new String(originalAsBase64);

        boolean legit = userMap.containsKey(userName) && Objects.equals(userMap.get(userName), passWord);

        if (legit){
            return byte64String;
        }
        else {
            throw new Exception("You shall not pass!");
        }
    }

    public boolean tokenIsOk(String token) {
        byte[] backAsBase64Bytes = token.getBytes();
        byte [] backAsBytes = Base64.getDecoder().decode(backAsBase64Bytes);
        String userName = new String(backAsBytes);
        var users = someDB.getUsers();
        return users.containsKey(userName);
    }
}
