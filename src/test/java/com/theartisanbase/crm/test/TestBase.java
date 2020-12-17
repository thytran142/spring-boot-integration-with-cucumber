/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test;

import com.fasterxml.jackson.databind.ObjectMapper;

abstract class TestBase {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
