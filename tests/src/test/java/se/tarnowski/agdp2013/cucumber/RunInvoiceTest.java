package se.tarnowski.agdp2013.cucumber;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:build/cucumber"})
public class RunInvoiceTest {
}
