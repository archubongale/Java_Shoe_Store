import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;


public class AppIntegrationTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();


  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @ClassRule
  public static DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Shoe Stores");
  }

  @Test
  public void brandFormIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or view a brand"));
    assertThat(pageSource()).contains("Add a new brand");
  }


  @Test
  public void storeFormIsDisplayed() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or view a store"));
    assertThat(pageSource()).contains("Add a new store");
  }

  @Test
  public void brandIsDisplayedWhenCreated() {
    goTo("http://localhost:4567/");
    click("a", withText("Add or view a brand"));
    fill("#name").with("New Balance");
    submit(".btn");
    assertThat(pageSource()).contains("New Balance");
  }

  @Test
  public void storeNameIsDisplayedInListWhenCreated() {
    goTo("http://localhost:4567/stores");
    fill("#name").with("Target");
    submit(".btn");
    assertThat(pageSource()).contains("Target");
  }

  @Test
    public void brandHasItsOwnPage() {
      Brand testBrand = new Brand("New Balance");
      testBrand.save();
      goTo("http://localhost:4567/brands");
      click("a", withText("New Balance"));
      assertThat(pageSource()).contains("Here are the stores that carry this brand");
    }

  }
