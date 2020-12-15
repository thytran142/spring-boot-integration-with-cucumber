
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

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
