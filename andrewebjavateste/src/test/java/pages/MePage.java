package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import suporte.Web;

public class MePage extends BasePage{
    public MePage(WebDriver navegador) {
        super(navegador);
    }
    public MePage clicarNaAbaMDBY(){
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

        return this;
    }
    public AddContactPage clicarbotaoMDBY (){
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        return new AddContactPage(navegador);
    }

}
