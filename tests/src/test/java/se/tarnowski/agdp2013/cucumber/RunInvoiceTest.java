package se.tarnowski.agdp2013.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

// This file isn't required if the feature is executed in directly in IDEA
@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:build/cucumber"})
public class RunInvoiceTest {
}
