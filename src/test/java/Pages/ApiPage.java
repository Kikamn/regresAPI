package Pages;

import Helper.EndPoint;
import Helper.Utility;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;


import java.io.File;
import java.util.List;

import static Helper.Models.getListUser;
import static Helper.Models.postNewUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiPage {
    String setURL;
    Response res;

    public void prepareValidLinkURLFor(String URL){
        switch (URL){
            case "GET_USER_REGRES":
                setURL = EndPoint.GET_USER_REGRES;
                break;
            case "CREATE_USER_REGRES":
                setURL = EndPoint.CREATE_USER_REGRES;
                break;
            default:
                System.out.println("input url yang benar");
        }
        System.out.println("Endpoint yang di gunakan :" + setURL);
    }
    public void hitAPIGetListData(){
        res = getListUser(setURL);
        System.out.println(res.getBody().asString());
    }
    public void hitAPIPostUser(){
        res = postNewUser(setURL);
        System.out.println(res.getBody().asString());
    }
    public void validationStatusCodeIsEquals(int statusCode){
        assertThat(res.statusCode()).isEqualTo(statusCode);

    }
    public void validationResponseBodyGetListUser(){
        String firstId = res.jsonPath().getString("data[0].id");
        String firstName = res.jsonPath().getString("data[0].first_name");
        String firstEmail = res.jsonPath().getString("data[0].email");
        String firstLastName = res.jsonPath().getString("data[0].last_name");
        String firstAvatar= res.jsonPath().getString("data[0].avatar");

        Assertions.assertThat(firstId).isNotNull();
        Assertions.assertThat(firstName).isNotNull();
        Assertions.assertThat(firstEmail).isNotNull();
        Assertions.assertThat(firstLastName).isNotNull();
        Assertions.assertThat(firstAvatar).isNotNull();
    }
    public void validationResponseJsonWhitJSONSchema(String fileJSON){
        File JSONFile = Utility.getJSONSchemaFile(fileJSON);
        res.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(JSONFile));
    }
    public void validationResponseBodyPostCreateNewUser(){
        JsonPath jsonPath = res.jsonPath();
        String name = jsonPath.get("name");
        String job = jsonPath.get("job");

        assertThat(name).isNotNull();
        assertThat(job).isNotNull();
    }

}
