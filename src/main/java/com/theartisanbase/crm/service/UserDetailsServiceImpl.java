/*
 * Created By Vanessa Tran at 2020 12 15
 */

package com.theartisanbase.crm.service;

import com.theartisanbase.crm.repo.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.theartisanbase.crm.domain.User applicationUser = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
    }
}
