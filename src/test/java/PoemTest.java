import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PoemTest {


    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        for (int i = 1; i <= 100; i++) {


            try {
                driver.get("https://www.shicimingju.com/paiming?p=" + i);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);


            Elements list = doc.select("div.shici_card");

            for (Element element : list) {


                System.out.println(element.select("div.list_num_info").text() + ";" + element.select("div.shici_list_main > h3").text() + ";" + element.select("div.shici_list_main > div.shici_content").text());

            }

        }


    }


}