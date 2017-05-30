package org.rocklass.raspalarm.config;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pbrochado on 30/05/2017.
 */
@Component
public class ConfigurationManager {
    private static final String CLOUD_MESSAGING_PROPERTIES_FILE_NAME = "fcmjava.properties";

    private final Properties cloudMessagingProperties;

    public ConfigurationManager() {
        super();
        cloudMessagingProperties = new Properties();

        try (final InputStream input = new FileInputStream(CLOUD_MESSAGING_PROPERTIES_FILE_NAME)) {
            cloudMessagingProperties.load(input);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public Properties getCloudMessagingProperties() {
       return cloudMessagingProperties;
    }
}
