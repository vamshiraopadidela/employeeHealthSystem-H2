package com.prokarma.employee.runner;

import org.junit.runner.RunWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.prokarma.employee.info","com.prokarma.employee.queue"})
public class EmployeeInfoRunner {

}
