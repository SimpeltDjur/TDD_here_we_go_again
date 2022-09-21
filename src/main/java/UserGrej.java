import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

public class UserGrej {

    SomeDB someDB;
    public UserGrej(SomeDB someDB) {
        this.someDB = someDB;
    }

    public String loggIn(String userName, String passWord) {

        HashMap<String, String> userMap = someDB.getUsers();

        String orginal = userName;
        byte[] orginalAsBytes = orginal.getBytes();
        byte[] orginalAsBase64 = Base64.getEncoder().encode(orginalAsBytes);
        String byte64String = new String(orginalAsBase64);



        boolean legit = userMap.containsKey(userName) && Objects.equals(userMap.get(userName), passWord);

        if (legit){
            return byte64String;
        }
        else {
            return "Bajs";
        }

    }
}
