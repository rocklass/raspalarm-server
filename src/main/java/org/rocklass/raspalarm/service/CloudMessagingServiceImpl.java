package org.rocklass.raspalarm.service;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import org.rocklass.raspalarm.messaging.CloudMessagingService;
import org.rocklass.raspalarm.messaging.NotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rocklass on 29/05/2017.
 */
@Component
public class CloudMessagingServiceImpl implements CloudMessagingService {
    private static final String PROPERTIES_FILE_NAME = "fcmjava.properties";

    @Autowired
    NotificationFactory notificationFactory;

    private final Properties properties;

    public CloudMessagingServiceImpl() {
        super();
        properties = new Properties();

        try (final InputStream input = new FileInputStream(PROPERTIES_FILE_NAME)) {
            properties.load(input);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sendMessage(String title, String content) {
        try (final IFcmClient client = new FcmClient(PropertiesBasedSettings.createFromProperties(properties))) {
            final FcmMessageResponse response = client.send(notificationFactory.getNotification(title, content));
            return response.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
