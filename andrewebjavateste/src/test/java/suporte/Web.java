package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome(){
        //Abrir o Navergador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Pichau\\Drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Função de timeout em SEGUNDOS

        //Navegando na pagina do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        return navegador;
    }
}
