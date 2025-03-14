package utils;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.IOException;

import static tests.TestsBase.driver;

public class ScreenshotsHandler {

    // Function to Take screenshot
    public static void TakeScreenshot(String FileName)
    {
        // Creating instance of File
        File File = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(File, new File("src/test/execution-results/screenshots/"  + FileName + ".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
