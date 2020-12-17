
/*
 * Created By Vanessa Tran at 2020 12 14
 */

package com.theartisanbase.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.theartisanbase.crm.error.validator.UserRegisterEmailUniqueConstraint;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(required=false, hidden=true) // Hide this field in Swagger
    private Long id;

    @Column(name="first_name", nullable = false)
    @NotEmpty(message = "First name is mandatory.")
    private String firstName;

    @Column(name="last_name", nullable = false)
    @NotEmpty(message = "Last name is mandatory.")
    private String lastName;

    @Column(name="password", nullable = false)
    @NotEmpty(message = "Password is mandatory.")
    @JsonIgnore
    private String password;

    @Column(name="email", nullable = false, unique = true)
    @NotEmpty(message = "Email is mandatory.")
    @Email(message = "Email must be valid.")
    @UserRegisterEmailUniqueConstraint
    private String email;

    @Column(name="status")
    @Enumerated
    @ApiModelProperty(required=false, hidden=true)
    private UserStatus status;
    public User() {
        this.status = UserStatus.PENDING_ACTIVATED;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName First name of a user
     * @return User
     */
    public User setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName of a user
     * @return User
     */
    public User setLastName(String lastName) {
        this.lastName = lastName;

        return this;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password Password of a user
     * @return User
     */
    @JsonProperty
    public User setPassword(String password) {
        this.password = password;

        return this;
    }

    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email Email of a user
     * @return User
     */
    public User setEmail(String email) {
        this.email = email;

        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
