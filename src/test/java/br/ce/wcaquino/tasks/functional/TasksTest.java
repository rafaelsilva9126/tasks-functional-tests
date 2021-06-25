package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {

	public WebDriver acessarAplicação() {
		System.setProperty("webdriver.chrome.driver", ("C:\\Users\\Rafa\\Desktop\\drivers\\chromedriver.exe"));
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8006/tasks/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	@Test
	public void deveSalvarTarefaComSucesso() throws InterruptedException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste Rafael 2");
			driver.findElement(By.id("dueDate")).sendKeys("27/06/2021");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Sucess!", msg);
		} finally {
			driver.close();
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefa() throws InterruptedException {
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
	public void naoDeveSalvarTarefaSemData() throws InterruptedException {
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
	public void deveSalvarTarefaSemDescricao() throws InterruptedException {
		WebDriver driver = acessarAplicação();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("27/06/2021");
			driver.findElement(By.id("saveButton")).click();
			String msg = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", msg);
		} finally {
			driver.close();
			driver.quit();
		}

	}
}
