package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty",
                "summary",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report",
                "html:target/cucumber-reports.html"},
        snippets = CAMELCASE,
        glue = {"stepdefinitions"}

)
public class Runner {
    private Runner() {}
}
