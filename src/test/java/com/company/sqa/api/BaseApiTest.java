package com.company.sqa.api;

import com.company.sqa.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {
    protected static final Logger logger = LogManager.getLogger(BaseApiTest.class);
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeClass(alwaysRun = true)
    public static void setupApiSpecs() {
        String baseUrl = ConfigReader.getApiBaseUrl();
        logger.info("Initializing REST Assured base specification with base URL: {}", baseUrl);

        RestAssured.baseURI = baseUrl;

        requestSpec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("X-API-KEY", ConfigReader.getApiKey())
                .log(LogDetail.URI)
                .log(LogDetail.METHOD)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.STATUS)
                .build();
    }

    public static RequestSpecification getRequestSpec() {
        if (requestSpec == null) {
            setupApiSpecs();
        }
        return requestSpec;
    }
}
