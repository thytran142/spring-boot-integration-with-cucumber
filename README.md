### How to integrate Cucumber Gherkin BDD tests with SpringBoot integration testing
I'm sure most of us, who want to integrate Gherkin BDD with Spring Boot Rest API project, will once face some difficulties like what I did. 
First of all, when I tried to search online, there are many repositories integrating RestTemplate with a Spring Boot project so Cucumber feature tests can call the API and run the test themselves. However, I find it is not what I want to achieve. I don't like to re-write tests where I have already tested some by MockMvc using SpringBoot runner. So I created this Repo as my experiment and it really works.
#### UI Documentation
http://localhost:8080/swagger-ui/index.html#

#### Take care of SpringIntegrationTest and StepDefinitionIntegrationTest

Example of a feature test:
```
Feature: Sign up as a new user
  In order to sign up as a new user
  I need to be able to sign up a new account.
  Scenario: Sign up a new user with a new email address
    When I send a "POST" api request to "/users/sign-up" with body:
    """
    {
      "firstName": "Vanessa",
      "lastName": "Tran",
      "email": "vanessa@vanntechs.com",
      "password": "1234567"
    }
    """
    And print last JSON response
    Then the response status code should be 201
    Then print last JSON response
```

Then integrate with StepDefinitionIntegrationTest as below:
```
@CucumberContextConfiguration
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class StepDefinitionIntegrationTest extends SpringIntegrationTest {
    @When("I send a {string} api request to {string}")
    public void iSendAApiRequestTo(String method, String endpoint) {
        try {
            if (method.equals("GET")) {
                this.executeGet(endpoint);
            }
            assertThat(true).isTrue();
        } catch (Exception e) {
            log.error("[Cucumber] Exception " + e.getMessage());
            assertThat(true).isFalse();
        }
    }
}
```
I used a separate testing database for my tests (you can use in-memory H2 database as default if you want)
You can use MockMvc under SpringIntegrationTest to call endpoint and print out response as you want. There are many other functions needed to write here, but I will leave it to you. 
