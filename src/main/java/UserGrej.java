import java.util.HashMap;

public class UserGrej {

    SomeDB someDB;
    public UserGrej(SomeDB someDB) {
        this.someDB = someDB;
    }

    public boolean loggIn(String userName, String passWord) {

        HashMap<String, String> userMap = someDB.getUsers();



        return userMap.containsKey(userName) ? userMap.get(userName) == passWord : false;
    }
}
