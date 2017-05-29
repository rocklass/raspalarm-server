package org.rocklass.raspalarm.service;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.rocklass.raspalarm.model.AlertData;
import org.springframework.stereotype.Component;
import org.rocklass.raspalarm.messaging.CloudMessagingService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Created by rocklass on 29/05/2017.
 */
@Component
public class CloudMessagingServiceImpl implements CloudMessagingService {
    private static final String PROPERTIES_FILE_NAME = "fcmjava.properties";

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
            final FcmMessageOptions options = FcmMessageOptions.builder()
                    .setTimeToLive(Duration.ofHours(1))
                    .build();

            final TopicMessageResponse response = client.send(new TopicUnicastMessage(options, new Topic(title), new AlertData(title, content)));

            return response.toString();
        } catch (final Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
