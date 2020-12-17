/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test.repo;

import com.theartisanbase.crm.CrmApplication;
import com.theartisanbase.crm.domain.User;
import com.theartisanbase.crm.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void should_find_no_users_if_repository_is_empty() {
        Iterable<User> users = this.userRepository.findAll();
        assertThat(users).isEmpty();
    }
    @Test
    @Transactional
    public void should_save_a_user()
    {
        User saveUser = new User();
        saveUser.setFirstName("Vanessa");
        saveUser.setLastName("Tran");
        saveUser.setEmail("vanessa@vanntechs.com");
        saveUser.setPassword("1234567");
        User result = this.userRepository.save(saveUser);
        assertThat(result).hasFieldOrPropertyWithValue("firstName", "Vanessa");
        assertThat(result).hasFieldOrPropertyWithValue("lastName", "Tran");
        assertThat(result).hasFieldOrPropertyWithValue("email", "vanessa@vanntechs.com");
    }
    @Test
    @Transactional
    public void should_find_user_by_email()
    {
        User user = this.userRepository.findByEmail("vanessa@vanntechs.com").orElse(null);
        assertThat(user).isNull();
        User sampleUser = new User().setEmail("vanessa@vanntechs.com")
                .setFirstName("Vanessa").setLastName("Tran").setPassword("1234567");
        this.testEntityManager.persist(sampleUser);

        user = this.userRepository.findByEmail("vanessa@vanntechs.com").orElse(null);
        assertThat(user).isNotNull();
        assertThat(user).hasFieldOrPropertyWithValue("email", "vanessa@vanntechs.com");
    }
}
