package Pages;

import Helper.EndPoint;
import Helper.Utility;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
        List<Object> id = res.jsonPath().getList("id");
        List<Object> email = res.jsonPath().getList("email");
        List<Object> first_name = res.jsonPath().getList("first_name");
        List<Object> last_name = res.jsonPath().getList("last_name");
        List<Object> avatar = res.jsonPath().getList("avatar");

        assertThat(id.get(0)).isNotNull();
        assertThat(email.get(0)).isNotNull();
        assertThat(first_name.get(0)).isNotNull();
        assertThat(last_name.get(0)).isNotNull();
        assertThat(avatar.get(0)).isNotNull();
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
