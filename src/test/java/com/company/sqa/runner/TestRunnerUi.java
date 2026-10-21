package com.company.sqa.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {
                "com.company.sqa.ui.steps",
                "com.company.sqa.ui.hooks"
        },
        tags = "@ui",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-ui-report.html",
                "json:target/cucumber-reports/cucumber-ui-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunnerUi extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
