package com.company.sqa.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private static final int maxTry = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess() && count < maxTry) {
            count++;
            logger.warn("Retrying test '{}' with status {} (Attempt {}/{})",
                    result.getName(), result.getStatus(), count, maxTry);
            return true;
        }
        return false;
    }
}
