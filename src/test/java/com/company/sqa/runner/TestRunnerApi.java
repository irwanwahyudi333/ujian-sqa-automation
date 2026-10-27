package com.company.sqa.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"com.company.sqa.api.steps"},
        tags = "@api",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-api-report.html",
                "json:target/cucumber-reports/cucumber-api-report.json",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        },
        monochrome = true
)
public class TestRunnerApi extends AbstractTestNGCucumberTests {
    @org.testng.annotations.BeforeSuite(alwaysRun = true)
    public void startLocalWebServer() {
        String apiUrl = com.company.sqa.utils.ConfigReader.getApiBaseUrl();
        if (apiUrl != null && (apiUrl.contains("localhost") || apiUrl.contains("127.0.0.1"))) {
            com.company.sqa.utils.LocalWebServer.start();
        }
    }

    @org.testng.annotations.AfterSuite(alwaysRun = true)
    public void stopLocalWebServer() {
        com.company.sqa.utils.LocalWebServer.stop();
    }

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
