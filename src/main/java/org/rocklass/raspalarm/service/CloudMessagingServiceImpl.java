package org.rocklass.raspalarm.service;

import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.http.client.IFcmClient;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.springframework.stereotype.Component;
import org.rocklass.raspalarm.api.CloudMessagingService;

import java.time.Duration;

/**
 * Created by rocklass on 29/05/2017.
 */
@Component
public class CloudMessagingServiceImpl implements CloudMessagingService {
    @Override
    public String sendMessage(String title, String content) {
        try (final IFcmClient client = new FcmClient(PropertiesBasedSettings.createFromDefault())) {
            final FcmMessageOptions options = FcmMessageOptions.builder()
                    .setTimeToLive(Duration.ofHours(1))
                    .build();

            final TopicMessageResponse response = client.send(new TopicUnicastMessage(options, new Topic(title), content));

            return response.toString();
        } catch (final Exception e) {
            return e.getMessage();
        }
    }
}
