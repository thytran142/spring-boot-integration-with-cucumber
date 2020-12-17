/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class VersionController {
    @GetMapping("/version")
    public Map<String, String> getVersion() {
        HashMap<String, String> version = new HashMap<>();
        version.put("version", "1.0");
        return version;
    }
}
