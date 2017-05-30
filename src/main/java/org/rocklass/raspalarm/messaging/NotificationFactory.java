package org.rocklass.raspalarm.messaging;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.builders.NotificationPayloadBuilder;
import de.bytefish.fcmjava.requests.notification.NotificationMulticastMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;

/**
 * Created by pbrochado on 30/05/2017.
 */
@Component
public class NotificationFactory {
    @Autowired
    DeviceManager deviceManager;

    public NotificationMulticastMessage getNotification(final String title, final String content) {
        final FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build();

        return new NotificationMulticastMessage(options, new ArrayList<>(deviceManager.getDevices()), new NotificationPayloadBuilder().setTitle(title).setBody(content).build());
    }
}
