package org.rocklass.raspalarm.messaging;

import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.requests.builders.NotificationPayloadBuilder;
import de.bytefish.fcmjava.requests.notification.NotificationMulticastMessage;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;

/**
 * Created by pbrochado on 30/05/2017.
 */
@Component
public class NotificationFactory {
    public NotificationMulticastMessage getNotification(final String title, final String content) {
        final FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(1))
                .build();

        // TODO get token from DB
        return new NotificationMulticastMessage(options, Arrays.asList("dqGLsdFWevI:APA91bHieBbdVFvdxscPq8DouRxpuybDPcbWyAnI2t5CON5n4a92oGD2bnvCy-UH6nzh6xF8Ynb5qePpwNWqq3AfCKrunBzMrDvHVcSNbobiMrWd1H2qhop5ZPD_wvnA9V7hKEGBjyc9"), new NotificationPayloadBuilder().setTitle(title).setBody(content).build());
    }
}
