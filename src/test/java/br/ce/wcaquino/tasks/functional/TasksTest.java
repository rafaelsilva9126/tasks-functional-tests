package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {

	public WebDriver acessarAplicação() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		//System.setProperty("webdriver.chrome.driver", ("C:\\Users\\Rafa\\Desktop\\drivers\\chromedriver.exe"));
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.15.10:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.15.10:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	@Test
	public void deveSalvarTarefaComSucesso() throws InterruptedException, MalformedURLException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste Rafael 2");
			driver.findElement(By.id("dueDate")).sendKeys("27/05/2022");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", msg);
		} finally {
			driver.close();
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefa() throws InterruptedException, MalformedURLException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste Rafael 2");
			driver.findElement(By.id("dueDate")).sendKeys("27/06/2019");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", msg);

		} finally {
			driver.close();
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws InterruptedException, MalformedURLException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste Rafael 2");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", msg);

		} finally {
			driver.close();
			driver.quit();
		}

	}
	@Test
	public void deveSalvarTarefaSemDescricao() throws InterruptedException, MalformedURLException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("27/07/2021");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", msg);
		} finally {
			driver.close();
			driver.quit();
		}

	}
}
