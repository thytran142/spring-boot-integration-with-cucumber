/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.security;
import com.auth0.jwt.JWT;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.theartisanbase.crm.security.SecurityConstants.EXPIRATION_TIME;
import static com.theartisanbase.crm.security.SecurityConstants.SECRET;

/**
 * This service is mainly for testing purpose.
 */
public class TokenAuthenticationService {
    /**
     * Generate a token for a specified email. If an email doesn't exist in the database, this function still be able to generate the token.
     * @param email username of a logged in user
     * @return a token to send for a request's header.
     */
    public static String createToken(String email) {
        String token = JWT.create()
                .withSubject(email)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        return token;
    }
}
