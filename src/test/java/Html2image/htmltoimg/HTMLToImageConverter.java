package Html2image.htmltoimg;


import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;





public class HTMLToImageConverter{
    static Path path = Paths.get("");
	static String directoryName = path.toAbsolutePath().toString();

    public static void main(String[] args) {
        String htmlFilePath = directoryName +  "/certificate.html";
        String outputPath = directoryName + "/output.png";
    
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run Chrome in headless mode
        WebDriver driver = new ChromeDriver(options);

        // Load the HTML content
        driver.get("file:///" + htmlFilePath);

        // Capture a screenshot of the rendered HTML
        BufferedImage image = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver)
                .getImage();

        // Save the screenshot as an image file
        try {
            File outputFile = new File(outputPath);
            ImageIO.write(image, "png", outputFile);
            System.out.println("HTML converted to image successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the WebDriver
        driver.quit();
    }
  
    
}
