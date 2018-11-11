package config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import static constant.Enviroment.CHROME_WEB_DRIVER_KEY;
import static constant.Enviroment.CHROME_WEB_DRIVER_PATH;
import static constant.File.PROPERTIES_FILE_PATH;

public class ConfigurationService {

    private Properties configProperties;

    public void setUpDriverEnvironment() {
        System.setProperty(CHROME_WEB_DRIVER_KEY, CHROME_WEB_DRIVER_PATH);
    }

    public Properties getConfigProperties() throws Exception {
        if (configProperties != null) {
            return configProperties;
        }
        InputStream configInput = new FileInputStream(PROPERTIES_FILE_PATH);
        configProperties = new Properties();
        configProperties.load(configInput);
        configInput.close();
        return configProperties;
    }
}
