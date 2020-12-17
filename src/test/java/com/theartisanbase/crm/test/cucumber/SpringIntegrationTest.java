/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test.cucumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theartisanbase.crm.CrmApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
@AutoConfigureMockMvc
// @AutoConfigureTestEntityManager
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class SpringIntegrationTest {
    private MvcResult response;
    @Autowired
    private MockMvc mockMvc;
    /**
     * Calling a GET request
     * @param endpoint Endpoint of a HTTP Request
     * @throws Exception if there are any exceptions
     */
    public void executeGet(String endpoint) throws Exception{
        log.info("[Cucumber] Calling a GET method to " + endpoint);
        this.response = this.mockMvc.perform(MockMvcRequestBuilders.get(endpoint)).andReturn();
    }
    public void executePost(String endpoint, String requestBody) throws Exception {
        log.info("[Cucumber] Calling a POST method to " + endpoint);
        this.response = this.mockMvc.perform(MockMvcRequestBuilders
        .post(endpoint)
        .content(requestBody)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)).andReturn();
    }

    /**
     *
     * @return status code of a HTTP request
     */
    public int getStatusCode() {
        return this.response.getResponse().getStatus();
    }

    /**
     * Test response body is json typed
     * @throws IOException json parse exception
     */
    public void checkJsonBody() throws IOException {
        String body = this.response.getResponse().getContentAsString();
        assertThat(body).isNotNull();
        assertThat(body).isNotEmpty();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.readValue(body, Map.class);
    }
    public void printLastJsonResponse() throws UnsupportedEncodingException {
        log.info("[Cucumber]Print last JSON Response " + this.response.getResponse().getContentAsString());
    }
}
