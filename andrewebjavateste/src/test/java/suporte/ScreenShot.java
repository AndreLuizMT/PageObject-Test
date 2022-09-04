package suporte;

import com.sun.jna.platform.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ScreenShot {
    public static void tirar(WebDriver navegador, String arquivo){
        File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(screenshot, new File(arquivo));
        }  catch (Exception e){
            System.out.println("Houveram problemas ao copiar o arquivo para a pasta" +e.getMessage());
        }
    }
}
