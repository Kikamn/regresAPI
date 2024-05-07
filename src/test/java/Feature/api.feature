Feature: Test API

  @apiGet
  Scenario: User can GET in API
    Given Prepare valid link URL for "GET_USER_REGRES"
    And Hit API get list data
    Then Validation status code is equals 200
    Then Validation response body get list user
    Then Validation response json whit JSONSchema "get_list_user.json"

  @apiPost
  Scenario: User can POST in API
    Given Prepare valid link URL for "CREATE_USER_REGRES"
    And Hit API Post user
    Then Validation status code is equals 201
    Then Validation response body post create new user
    #Then Validation response json whit JSONSchema "post_create_user.json"