/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test;

import com.theartisanbase.crm.domain.User;
import com.theartisanbase.crm.repo.UserRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest extends TestBase {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testSignUpSuccessPath() throws Exception {
        String requestBody = new JSONObject().put("firstName", "Vanessa")
                .put("lastName", "Tran")
                .put("email", "thytran142@hotmail.com")
                .put("password", "1234567")
                .toString();
        this.mockMvc.perform(MockMvcRequestBuilders
        .post("/users/sign-up")
        .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
