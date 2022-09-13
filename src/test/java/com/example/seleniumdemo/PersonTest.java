package com.example.seleniumdemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonTest {

	WebDriver driver;

	@LocalServerPort
	int port;

	@BeforeAll
	static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setupTest() {
		driver = new ChromeDriver();
	}

	@AfterEach
	void teardown() {
		driver.quit();
	}

	@Test
	void test() {
		driver.get("http://localhost:" + port + "/person");

		final String title = driver.getTitle();

		assertThat(title).contains("Person Form");
	}

	@Test
	void submitForm() {
		driver.get("http://localhost:" + port + "/person");

		driver.findElement(By.name("name")).sendKeys("Leonardo");
		driver.findElement(By.name("age")).sendKeys("33");
		driver.findElement(By.id("submit")).click();

		final String bodyText = driver.findElement(By.tagName("body")).getText();
		assertThat(bodyText).contains("Congratulations!");
	}

}
