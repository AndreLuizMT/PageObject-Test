package testes;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Parallel;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import suporte.Generator;
import suporte.ScreenShot;
import suporte.Web;

import java.util.concurrent.TimeUnit;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTest.csv")

public class InformacoesUsuarioTest {
    private WebDriver navegador;
    @Rule
    public TestName test = new TestName();

    @Before
    public void setup(){
        navegador = Web.createChrome();

        //Abrir o Navergador
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Pichau\\Drivers\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //Função de timeout em SEGUNDOS

        //Navegando na pagina do Taskit
        navegador.get("http://www.juliodelima.com.br/taskit");

        //Clicar no link que possui o texto Sign in
        //WebElement.linkSignIn = navegador.findElement(By.linkText("Sign in")); -- utilizar em casos de muitas ações
        navegador.findElement(By.linkText("Sign in")).click();

        //Identificando o formulario de login
        WebElement formularioSignInbox = navegador.findElement(By.id("signinbox"));

        //Digitar no campo com name "login" que está dentro do formulário de id "signinbox" o texto "luiz002"
        formularioSignInbox.findElement(By.name("login")).sendKeys("luiz002");

        //Digitar no campo com name "password" dentro do formulário de id "signinbox" o texto "123456"
        formularioSignInbox.findElement(By.name("password")).sendKeys("123456");

        //Clicar no link com o texto "SIGN IN"
        navegador.findElement(By.linkText("SIGN IN")).click();

        // Clicar em um link que possui a classe "me"
        navegador.findElement(By.className("me")).click();

        // Clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }
    @Test
    public void testAdicionarUmaInformacaoAdicionaldoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato,@Param(name="mensagem")String mensagemesperada){


        // Clicar no botão através do seu xpath //button[@data-target="addmoredata"]
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();

        // Identificar a popup onde está o formulário de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));

        // Na combo de name "type" escolher a opção Phone
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);             //função de selecção de combobox

        // No campo de nome contact digitar +5588999991111
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        // Clicar No link de text "SAVE" que esta na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        // Na Mensagem de id "toast-container" validar que o texto "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals(mensagemesperada, mensagem);


    }

    @Test
    public void removerUmContatoDeUmUsuario() {
        // Clicar no elemento pelo xpath //span[text()="+551133334444"]/following-subling::a
        navegador.findElement(By.xpath("//span[text()=\"+551133334444\"]/following-sibling::a")).click();

        // Confirmar a janela Javascript
        navegador.switchTo().alert().accept(); // comando para alertas javascript

        // Validar o texto apresentada foi Rest in peace, dear phone
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        Assert.assertEquals("Rest in peace, dear phone!", mensagem);

        String screenshotArquivo = "C:\\Users\\Pichau\\Desktop\\Prints de Testes\\Junit-selenium" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        ScreenShot.tirar(navegador, screenshotArquivo);
        // Aguardar até 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop)); // comando para junit esperar um elemento sumir

        // Clicar no link com texto Logout
        navegador.findElement(By.linkText("Logout"));
    }
    @After
    public void tearDown(){
        // Fechar o navegador
        navegador.quit();
    }
}
