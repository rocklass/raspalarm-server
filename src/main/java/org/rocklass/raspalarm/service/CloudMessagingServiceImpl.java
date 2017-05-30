package org.rocklass.raspalarm.service;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import org.rocklass.raspalarm.config.ConfigurationManager;
import org.rocklass.raspalarm.messaging.CloudMessagingService;
import org.rocklass.raspalarm.messaging.NotificationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rocklass on 29/05/2017.
 */
@Component
public class CloudMessagingServiceImpl implements CloudMessagingService {
    @Autowired
    ConfigurationManager configurationManager;
    @Autowired
    NotificationFactory notificationFactory;

    @Override
    public String sendMessage(String title, String content) {
        try (final IFcmClient client = new FcmClient(PropertiesBasedSettings.createFromProperties(configurationManager.getCloudMessagingProperties()))) {
            final FcmMessageResponse response = client.send(notificationFactory.getNotification(title, content));
            return response.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
