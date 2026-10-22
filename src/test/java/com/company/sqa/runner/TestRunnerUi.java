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
    @org.testng.annotations.BeforeSuite(alwaysRun = true)
    public void startLocalWebServer() {
        String uiUrl = com.company.sqa.utils.ConfigReader.getUiBaseUrl();
        if (uiUrl != null && (uiUrl.contains("localhost") || uiUrl.contains("127.0.0.1"))) {
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
