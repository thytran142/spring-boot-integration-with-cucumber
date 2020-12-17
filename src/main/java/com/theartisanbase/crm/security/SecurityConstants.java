/*
 * Created By Vanessa Tran at 2020 12 14
 */

package com.theartisanbase.crm.security;

public class SecurityConstants {
    // todo: later use secret key that is used on PROD
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/sign-up";
    public static final String LOGIN_URL = "/login";
    public static final String VERSION_URL = "/version";
}
