/*
 * Created By Vanessa Tran at 2020 12 14
 */

package com.theartisanbase.crm.web;

import com.theartisanbase.crm.domain.User;
import com.theartisanbase.crm.domain.UserStatus;
import com.theartisanbase.crm.repo.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    protected UserController() {
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody User user) {
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(UserStatus.PENDING_ACTIVATED);
        this.userRepository.save(user);
    }

}
