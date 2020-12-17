/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test;

import com.theartisanbase.crm.security.TokenAuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.theartisanbase.crm.security.SecurityConstants.TOKEN_PREFIX;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationServiceTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void shouldNotAllowAccessToUnAuthenticationUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isForbidden());
    }
    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = TokenAuthenticationService.createToken("thytran142@hotmail.com");
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/users").header("Authorization", TOKEN_PREFIX + token)).andExpect(status().isOk());
    }
}
