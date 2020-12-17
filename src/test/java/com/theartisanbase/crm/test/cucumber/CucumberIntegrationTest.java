/*
 * Created By Vanessa Tran at 2020 12 17
 */

package com.theartisanbase.crm.test.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/theartisanbase/crm/test/cucumber/features")
public class CucumberIntegrationTest {
}
