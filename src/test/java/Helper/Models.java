package Helper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

public class Models {
    private static RequestSpecification request;

    public static void setupHeader(){
        request = RestAssured.given()
                .header("Content-type", "application/json")
                .header("Accept", "application/json");
    }
    public static Response getListUser(String endPoint){
        setupHeader();
        return request.when().get(endPoint);
    }
    public static Response postNewUser(String endPoint){
        String name = "Noval";
        String job = "QA";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("job", job);

        setupHeader();
        return request.body(jsonObject.toString()).when().post(endPoint);
    }

}
