package StepDef;

import Pages.ApiPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class ApiStep {
    ApiPage apiPage;
    public ApiStep(){
        this.apiPage = new ApiPage();
    }

    @Given("Prepare valid link URL for {string}")
    public void prepareValidLinkURLFor(String URL) {
        apiPage.prepareValidLinkURLFor(URL);
    }

    @And("Hit API get list data")
    public void hitAPIGetListData() {
        apiPage.hitAPIGetListData();
    }

    @Then("Validation status code is equals {int}")
    public void validationStatusCodeIsEquals(int statusCode) {
        apiPage.validationStatusCodeIsEquals(statusCode);
    }

    @Then("Validation response body get list user")
    public void validationResponseBodyGetListUser() {
        apiPage.validationResponseBodyGetListUser();
    }

    @Then("Validation response json whit JSONSchema {string}")
    public void validationResponseJsonWhitJSONSchema(String fileJSON) {
        apiPage.validationResponseJsonWhitJSONSchema(fileJSON);
    }

    @And("Hit API Post user")
    public void hitAPIPostUser() {
        apiPage.hitAPIPostUser();
    }

    @Then("Validation response body post create new user")
    public void validationResponseBodyPostCreateNewUser() {
        apiPage.validationResponseBodyPostCreateNewUser();
    }
}
