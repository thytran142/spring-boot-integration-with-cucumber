/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test.cucumber;

import com.theartisanbase.crm.CrmApplication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
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


    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        log.info("[Cucumber] Checking if the response status code should be " + statusCode);
        assertThat(getStatusCode()).isEqualTo(statusCode);
    }

    @And("the response should be in JSON")
    public void theResponseShouldBeInJSON() {
        try{
            checkJsonBody();
            assertThat("json").isEqualTo("json");
        }catch(IOException e) {
            log.error("[Cucumber] the response should be in JSON  has an exception " + e.getMessage());
            assertThat("json").isEqualTo(e.getMessage());
        }
    }

    @When("I send a {string} api request to {string} with body:")
    public void iSendAApiRequestToWithBody(String method, String endpoint, String body) {
       try{
           if (method.equals("POST")) {
               executePost(endpoint, body);
               assertThat(true).isTrue();
           }
       }catch (Exception e) {
           e.getStackTrace();
           assertThat("[Cucumber] Send " + method + " Method").isEqualTo(e.getMessage());
       }
    }

    @Then("print last JSON response")
    public void printLastJSONResponse() {
        try{
            printLastJsonResponse();
            assertThat(true).isTrue();
        }catch(Exception e) {
            assertThat("json response").isEqualTo(e.getMessage());
        }
    }
}
