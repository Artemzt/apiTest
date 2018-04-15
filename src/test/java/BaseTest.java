import io.restassured.response.Response;

import java.util.ArrayList;

public class BaseTest {

    protected static final String BASE_URL = "https://swapi.co/api/";

    public ArrayList getValue(Response response, String string) {
       return response.path(string);
    }


}
