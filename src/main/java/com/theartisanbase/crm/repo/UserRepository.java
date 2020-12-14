/*
 * Created By Vanessa Tran at 2020 12 14
 */

package com.theartisanbase.crm.repo;

import com.theartisanbase.crm.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Find user by email
     * @param email Email of a user
     * @return Optional of User
     */
    Optional<User> findByEmail(@Param("email") String email);
}
