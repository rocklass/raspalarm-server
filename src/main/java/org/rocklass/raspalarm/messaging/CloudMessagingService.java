package org.rocklass.raspalarm.messaging;

/**
 * Created by rocklass on 29/05/2017.
 */
public interface CloudMessagingService {
    String sendMessage(String title, String content);
}
